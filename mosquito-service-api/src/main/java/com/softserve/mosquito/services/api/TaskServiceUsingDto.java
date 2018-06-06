package com.softserve.mosquito.services.api;

import com.softserve.mosquito.dtos.TaskDto;

import java.util.List;

public interface TaskServiceUsingDto{
    TaskDto create(TaskDto taskDto);
    TaskDto update(TaskDto taskDto);
    void delete(Long id);

    TaskDto read(Long id);
    List<TaskDto> readAll();

    TaskDto getTaskById(Long id);

    List<TaskDto> getAllTasks();

    List<TaskDto> filterTasksByParent(Long parentId);

    List<TaskDto> filterTasksByOwner(Long ownerId);

    List<TaskDto> filterTasksByWorker(Long workerId);

    List<TaskDto> filterTasksByPriority(Long priorityId);

    List<TaskDto> filterTasksByStatus(Long statusId);
}
