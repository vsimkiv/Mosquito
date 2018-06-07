package com.softserve.mosquito.services.impl;

import com.softserve.mosquito.dtos.TaskDto;
import com.softserve.mosquito.entities.Task;
import com.softserve.mosquito.repo.api.TaskRepo;
import com.softserve.mosquito.services.api.TaskServiceUsingDto;
import com.softserve.mosquito.transformer.impl.TaskTransformerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class TaskServiceUsingDtoImpl implements TaskServiceUsingDto {
    private TaskRepo taskRepo;

    @Autowired
    public TaskServiceUsingDtoImpl(TaskRepo taskRepo) {
        this.taskRepo = taskRepo;
    }

    @Override
    public TaskDto create(TaskDto taskDto) {
        return taskDto;
    }

    @Override
    public TaskDto update(TaskDto taskDto) {
        return taskDto;
    }

    @Override
    public void delete(Long id) {

    }
    @Override
    public TaskDto read(Long id) {
        return null;
    }

    @Transactional
    @Override
    public List<TaskDto> readAll() {
        List<Task> tasks = taskRepo.readAll();
        List<TaskDto> taskDtos = new ArrayList<>();

        for (Task task : tasks){
            taskDtos.add(TaskTransformerImpl.toDTO(task));
        }

        return taskDtos;
    }

    @Override
    public List<TaskDto> filterByParent(Long parentId) {
        return null;
    }

    @Override
    public List<TaskDto> filterByOwner(Long ownerId) {
        return null;
    }

    @Override
    public List<TaskDto> filterByWorker(Long workerId) {
        return null;
    }

    @Override
    public List<TaskDto> filterByPriority(Long priorityId) {
        return null;
    }

    @Override
    public List<TaskDto> filterByStatus(Long statusId) {
        return null;
    }

}
