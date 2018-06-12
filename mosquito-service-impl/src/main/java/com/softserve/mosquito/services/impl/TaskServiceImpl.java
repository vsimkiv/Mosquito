package com.softserve.mosquito.services.impl;

import com.softserve.mosquito.dtos.TaskDto;
import com.softserve.mosquito.entities.Task;
import com.softserve.mosquito.repo.api.TaskRepo;
import com.softserve.mosquito.services.api.TaskService;
import com.softserve.mosquito.transformer.impl.TaskTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static com.softserve.mosquito.transformer.impl.TaskTransformer.toDTO;

@Service
public class TaskServiceImpl implements TaskService {
    private TaskRepo taskRepo;

    @Autowired
    public TaskServiceImpl(TaskRepo taskRepo) {
        this.taskRepo = taskRepo;
    }

    @Transactional
    @Override
    public TaskDto save(TaskDto taskDto) {

        if (isTaskPresent(taskDto)) return TaskTransformer.toDTO(taskRepo.read(taskDto.getId()));

        Task task = taskRepo.create(TaskTransformer.toEntity(taskDto));

        if (task == null)
            return null;

        return toDTO(task);
    }

    @Transactional
    @Override
    public TaskDto update(TaskDto taskDto) {
        Task task = taskRepo.update(TaskTransformer.toEntity(taskDto));
        if (task == null)
            return null;
        return toDTO(task);
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
        TaskDto taskDto = toDTO(task);

        taskDto.setParentTaskDto(getParentTaskDto(taskDto.getId()));
        taskDto.setChildTaskDtoList(getSubTasks(taskDto.getId()));
        return taskDto;
    }

    @Transactional
    @Override
    public List<TaskDto> getSubTasks(Long id) {
        List<Task> tasks = taskRepo.getSubTasks(id);
        List<TaskDto> taskDtos = new ArrayList<>();
        for (Task task : tasks) {
            taskDtos.add(toDTO(task));
        }
        return taskDtos;
    }

    @Transactional
    @Override
    public TaskDto getParentTaskDto(Long parentId) {
        Task task = taskRepo.read(parentId);
        return toDTO(task);
    }

    @Transactional
    @Override
    public List<TaskDto> filterByOwner(Long ownerId) {
        return null;
    }

    @Transactional
    @Override
    public List<TaskDto> filterByWorker(Long workerId) {
        return null;
    }

    @Transactional
    @Override
    public List<TaskDto> filterByPriority(Long priorityId) {
        return null;
    }

    @Transactional
    @Override
    public List<TaskDto> filterByStatus(Long statusId) {
        return null;
    }

    private boolean isTaskPresent(TaskDto taskDto){
        if (getById(taskDto.getId())!=null) return true;
        return false;
    }

}
