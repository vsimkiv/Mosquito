package com.softserve.mosquito.services.api;

import com.softserve.mosquito.dtos.TaskDto;
import com.softserve.mosquito.entities.Task;

import java.util.List;

public interface TaskService {

    List<Task> getAllTasks();

    TaskDto getTaskById(Long id);

    Task createTask(TaskDto taskCreateDto);

    Task updateTask(Task task);

    void removeTask(Long id);

    List<Task> getTasksByOwner(Long ownerId);

    List<Task> getTasksByWorker(Long workerId);

    List<Task> getSubTasks(Long parentTaskId);

    List<Task> getTasksByOwnerAndStatus(Long ownerId, Byte statusId);

    List<Task> getTasksByWorkerAndStatus(Long workerId, Byte statusId);

    List<Task> getTasksByOwnerAndStatusAndParent(Long parentId, Long ownerId, Byte statusId);

    List<Task> getTasksByWorkerAndStatusAndParent(Long parentId, Long workerId, Byte statusId);
}
