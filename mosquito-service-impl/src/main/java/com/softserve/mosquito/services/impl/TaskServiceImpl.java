package com.softserve.mosquito.services.impl;

import com.softserve.mosquito.dtos.TaskDto;
import com.softserve.mosquito.entities.Task;
import com.softserve.mosquito.repo.api.TaskRepo;
import com.softserve.mosquito.services.api.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static com.softserve.mosquito.transformer.impl.TaskTransformer.toDTO;
import static com.softserve.mosquito.transformer.impl.TaskTransformer.toEntity;

@Service
public class TaskServiceImpl implements TaskService {
    private TaskRepo taskRepo;

    @Autowired
    public TaskServiceImpl(TaskRepo taskRepo) {
        this.taskRepo = taskRepo;
    }

    @Transactional
    @Override
    public TaskDto create(TaskDto taskDto) {
        Task task = taskRepo.create(toEntity(taskDto));

        if (task == null)
            return null;

        return toDTO(task);
    }

    @Transactional
    @Override
    public TaskDto update(TaskDto taskDto) {
        Task task = taskRepo.update(toEntity(taskDto));

        if (task == null)
            return null;

        return toDTO(task);
    }

    @Transactional
    @Override
    public void delete(Long id) {

    }

    @Transactional
    @Override
    public TaskDto read(Long id) {
        Task task = taskRepo.read(id);

        if (task == null)
            return null;

        return toDTO(task);
    }

    @Transactional
    @Override
    public List<TaskDto> readAll() {
        List<Task> tasks = taskRepo.readAll();
        List<TaskDto> taskDtos = new ArrayList<>();

        for (Task task : tasks){
            taskDtos.add(toDTO(task));
        }

        return taskDtos;
    }

    @Transactional
    @Override
    public List<TaskDto> filterByParent(Long parentId) {
        return null;
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

}
