package com.softserve.mosquito.services.api;

import com.softserve.mosquito.dtos.TaskFullDto;
import com.softserve.mosquito.dtos.TaskSimpleDto;

import java.util.List;

public interface TaskService {

    TaskFullDto save(TaskFullDto taskFullDto);

    TaskFullDto update(TaskFullDto taskFullDto);

    void delete(Long id);

    TaskFullDto getById(Long id);

    TaskFullDto getParentTaskDto(Long parentId);

    List<TaskFullDto> getSubTasks(Long id);

    List<TaskFullDto> getProjects();

    List<TaskFullDto> filterByOwner(Long ownerId);

    List<TaskFullDto> filterByWorker(Long workerId);

    List<TaskFullDto> filterByPriority(Long priorityId);

    List<TaskFullDto> filterByStatus(Long statusId);

    boolean isPresent(TaskFullDto taskFullDto);

    boolean isPresent(String name);

    TaskFullDto getByName(String name);

    TaskSimpleDto getSimpleTaskById(Long id);
}
