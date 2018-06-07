package com.softserve.mosquito.services.impl;

import com.softserve.mosquito.dtos.TaskDto;
import com.softserve.mosquito.services.api.TaskServiceUsingDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskServiceUsingDtoImpl implements TaskServiceUsingDto {

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

    @Override
    public List<TaskDto> readAll() {
        return new ArrayList<>();
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
