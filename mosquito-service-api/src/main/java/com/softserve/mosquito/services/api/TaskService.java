package com.softserve.mosquito.services.api;

import com.softserve.mosquito.entities.Task;

import java.util.List;

public interface TaskService {

    List<Task> getAllTasks();

    Task getTaskById(Long id);

    List<Task> getTasksByOwnerId(Long ownerId);

    List<Task> getTasksByWorkerId(Long workerId);

    List<Task> getSubTasks(Long parentTaskId);

    Task createTask(Task task);

    Task updateTask(Task task);

    void removeTask(Long id);

    List<Task> getTasksByOwnerAndStatus(Long ownerId, Long statusId);

    List<Task> getTasksByWorkerAndStatus(Long workerId, Long statusId);

    List<Task> getTasksByParentAndOwnerAndStatus(Long parentId, Long ownerId, Long statusId);

    List<Task> getTasksByParentAndWorkerAndStatus(Long parentId, Long workerId, Long statusId);
}
