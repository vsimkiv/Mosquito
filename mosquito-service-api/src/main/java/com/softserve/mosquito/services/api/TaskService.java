package com.softserve.mosquito.services.api;

import com.softserve.mosquito.dtos.TaskCreateDto;
import com.softserve.mosquito.entities.Task;

import java.util.List;

public interface TaskService {

    List<Task> getAllTasks();

    Task getTaskById(Long id);

    Task createTask(TaskCreateDto taskCreateDto);

    Task updateTask(Task task);

    void removeTask(Task task);

    List<Task> getTasksByOwner(Long ownerId);

    List<Task> getTasksByWorker(Long workerId);


    void removeTask(Long id);

    List<Task> getSubTasks(Long parentTaskId);


    List<Task> getTasksByOwnerAndStatus(Long ownerId, Byte statusId);

    List<Task> getTasksByWorkerAndStatus(Long workerId, Byte statusId);

    List<Task> getTasksByOwnerAndStatusAndParent(Long parentId, Long ownerId, Byte statusId);

    List<Task> getTasksByWorkerAndStatusAndParent(Long parentId, Long workerId, Byte statusId);
}
