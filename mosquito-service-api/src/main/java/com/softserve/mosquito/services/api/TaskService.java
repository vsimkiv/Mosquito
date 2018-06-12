package com.softserve.mosquito.services.api;

import com.softserve.mosquito.dtos.TaskDto;

import java.util.List;

public interface TaskService {

    TaskDto save(TaskDto taskDto);

    TaskDto update(TaskDto taskDto);

    void delete(Long id);

    TaskDto getById(Long id);

    TaskDto getParentTaskDto(Long parentId);

    List<TaskDto> getSubTasks(Long id);

    List<TaskDto> filterByOwner(Long ownerId);

    List<TaskDto> filterByWorker(Long workerId);

    List<TaskDto> filterByPriority(Long priorityId);

    List<TaskDto> filterByStatus(Long statusId);
}
