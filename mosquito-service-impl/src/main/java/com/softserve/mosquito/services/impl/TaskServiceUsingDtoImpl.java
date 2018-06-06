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
    public TaskDto getTaskById(Long id) {
        return null;
    }

    @Override
    public List<TaskDto> getAllTasks() {
        return null;
    }

    @Override
    public List<TaskDto> filterTasksByParent(Long parentId) {
        return null;
    }

    @Override
    public List<TaskDto> filterTasksByOwner(Long ownerId) {
        return null;
    }

    @Override
    public List<TaskDto> filterTasksByWorker(Long workerId) {
        return null;
    }

    @Override
    public List<TaskDto> filterTasksByPriority(Long priorityId) {
        return null;
    }

    @Override
    public List<TaskDto> filterTasksByStatus(Long statusId) {
        return null;
    }

}
