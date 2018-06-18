package com.softserve.mosquito.services.api;

import com.softserve.mosquito.dtos.TaskFullDto;
import com.softserve.mosquito.dtos.TaskSimpleDto;

import java.util.List;

public interface TaskService {

    TaskFullDto save(TaskFullDto taskFullDto);

    TaskFullDto update(TaskFullDto taskFullDto);

    void delete(Long id);

    TaskFullDto getById(Long id);


    TaskFullDto getParent(Long parentId);

    List<TaskFullDto> getSubTasks(Long id);


    List<TaskFullDto> getByOwner(Long ownerId);

    List<TaskFullDto> getByWorker(Long workerId);


    List<TaskFullDto> getAllProjects();

    List<TaskFullDto> getProjectsByOwner(Long ownerId);


    List<TaskFullDto> filterByStatus(List<TaskFullDto> taskFullDtoList, Long statusId);


    boolean isPresent(TaskFullDto taskFullDto);

    boolean isPresent(String name);

    TaskFullDto getByName(String name);

    TaskSimpleDto getSimpleTaskById(Long id);
}
