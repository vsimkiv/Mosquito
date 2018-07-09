package com.softserve.mosquito.services.impl;


import com.softserve.mosquito.dtos.EstimationDto;
import com.softserve.mosquito.dtos.TaskCreateDto;
import com.softserve.mosquito.dtos.TaskDto;
import com.softserve.mosquito.entities.Estimation;
import com.softserve.mosquito.entities.Task;
import com.softserve.mosquito.repo.api.TaskRepo;
import com.softserve.mosquito.services.api.*;
import com.softserve.mosquito.transformer.EstimationTransformer;
import com.softserve.mosquito.transformer.TaskTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.softserve.mosquito.transformer.TaskTransformer.toEntity;
import static com.softserve.mosquito.transformer.TaskTransformer.toTaskDto;
import static com.softserve.mosquito.transformer.TaskTransformer.toTaskDtoList;

@Service
public class TaskServiceImpl implements TaskService {
    private TaskRepo taskRepo;
    private UserService userService;
    private PriorityService priorityService;
    private StatusService statusService;
    private EstimationService estimationService;

    @Autowired
    public TaskServiceImpl(TaskRepo taskRepo, UserService userService, PriorityService priorityService, StatusService statusService, EstimationService estimationService) {
        this.taskRepo = taskRepo;
        this.userService = userService;
        this.priorityService = priorityService;
        this.statusService = statusService;
        this.estimationService = estimationService;
    }

    @Transactional
    @Override
    public TaskDto save(TaskCreateDto taskCreateDto) {
        Task task = toEntity(taskCreateDto);
        Estimation estimation = EstimationTransformer.toEntity(estimationService.createEstimation(EstimationDto.builder()
                .timeEstimation(taskCreateDto.getEstimationTime())
                .remaining(taskCreateDto.getEstimationTime())
                .build()));
        System.out.println("EstimationID: " + estimation.getId() + " time: " + estimation.getTimeEstimation());
        task.setEstimation(estimation);
        task = taskRepo.create(task);
        /*tasksBoardService.add(new TaskMongo(task.getId(), task.getName()), task.getOwner().getId(),
                task.getWorker().getId());*/
        return task == null ? null : toTaskDto(task);
    }

    @Transactional
    @Override
    public TaskDto update(TaskCreateDto taskCreateDto) {
        Task task = taskRepo.update(toEntity(taskCreateDto));
        if (task == null)
            return null;
        return toTaskDto(task);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        taskRepo.delete(id);
    }

    @Transactional
    @Override
    public TaskDto getById(Long id) {
        Task task = taskRepo.read(id);
        TaskDto taskDto = toTaskDto(task);

        return taskDto;
    }

    @Transactional
    @Override
    public TaskDto getParent(Long parentId) {
        return toTaskDto(taskRepo.read(parentId));
    }

    @Transactional
    @Override
    public List<TaskDto> getSubTasks(Long id) {
        return toTaskDtoList(taskRepo.getSubTasks(id));
    }

    @Transactional
    @Override
    public List<TaskDto> getByOwner(Long ownerId) {
        return TaskTransformer.toTaskDtoList(taskRepo.getByOwner(ownerId));
    }

    @Transactional
    @Override
    public List<TaskDto> getByWorker(Long workerId) {
        return toTaskDtoList(taskRepo.getByWorker(workerId));
    }

    @Transactional
    @Override
    public List<TaskDto> getAllProjects() {
        return toTaskDtoList(taskRepo.getAllProjects());
    }

    @Transactional
    @Override
    public List<TaskDto> getProjectsByOwner(Long ownerId) {
        return TaskTransformer.toTaskDtoList(taskRepo.getProjectsByOwner(ownerId));
    }

    @Transactional
    @Override
    public List<TaskDto> filterByStatus(List<TaskDto> taskDtoList, Long statusId) {
        List<TaskDto> filteredList = new ArrayList<>();
        for (TaskDto taskDto : taskDtoList) {
            if (taskDto.getStatus().equals(statusId)) {
                filteredList.add(taskDto);
            }
        }
        return filteredList;
    }

    @Transactional
    @Override
    public TaskDto getSimpleTaskById(Long id) {
        Task task = taskRepo.read(id);
        return toTaskDto(task);
    }

    @Transactional
    @Override
    public TaskDto getByTrelloId(String trelloId) {
        return toTaskDto(taskRepo.getByTrelloId(trelloId));
    }

    @Transactional
    @Override
    public boolean isPresent(String trelloId) {
        return (taskRepo.getByTrelloId(trelloId) != null);
    }

    @Transactional
    @Override
    public TaskDto getByName(String name) {
        return toTaskDto(taskRepo.getByName(name));
    }

}
