package com.softserve.mosquito.services.api;

import com.softserve.mosquito.dtos.TaskDto;

import java.util.List;

public interface TaskService {

    TaskDto create(TaskDto taskDto);

    TaskDto update(TaskDto taskDto);

    void delete(Long id);


    TaskDto read(Long id);

    List<TaskDto> readAll();


    List<TaskDto> filterByParent(Long parentId);

    List<TaskDto> filterByOwner(Long ownerId);

    List<TaskDto> filterByWorker(Long workerId);

    List<TaskDto> filterByPriority(Long priorityId);

    List<TaskDto> filterByStatus(Long statusId);
}
