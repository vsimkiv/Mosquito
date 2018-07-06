package com.softserve.mosquito.services.api;

import com.softserve.mosquito.dtos.TaskCreateDto;
import com.softserve.mosquito.dtos.TaskFullDto;
import com.softserve.mosquito.dtos.TaskSimpleDto;

import java.util.List;

public interface TaskService {

    //TaskFullDto save(TaskFullDto taskFullDto);

    TaskCreateDto save(TaskCreateDto taskCreateDto );

    //TaskFullDto update(TaskFullDto taskFullDto);

    TaskCreateDto update(TaskCreateDto taskCreateDto);

    void delete(Long id);

    //TaskFullDto getById(Long id);

    TaskCreateDto getById(Long id);


    //TaskFullDto getParent(Long parentId);

    TaskCreateDto getParent(Long parentId);

    //List<TaskFullDto> getSubTasks(Long id);

    List<TaskCreateDto> getSubTasks(Long id);

    //List<TaskFullDto> getByOwner(Long ownerId);

    List<TaskCreateDto> getByOwner(Long ownerId);

    //List<TaskFullDto> getByWorker(Long workerId);

    List<TaskCreateDto> getByWorker(Long workerId);

    //List<TaskFullDto> getAllProjects();

    List<TaskCreateDto> getAllProjects();

    //List<TaskFullDto> getProjectsByOwner(Long ownerId);

    List<TaskCreateDto> getProjectsByOwner(Long ownerId);

    //List<TaskFullDto> filterByStatus(List<TaskFullDto> taskFullDtoList, Long statusId);

    List<TaskCreateDto> filterByStatus(List<TaskCreateDto> taskCreateDtoList, Long statusId);

    boolean isPresent(String name);

    //TaskFullDto getByName(String name);

    TaskCreateDto getByName(String name);

    //TaskSimpleDto getSimpleTaskById(Long id);

    TaskCreateDto getSimpleTaskById(Long id);

    //TaskFullDto getByTrelloId(String trelloId);

    TaskCreateDto getByTrelloId(String trelloId);
}
