package com.softserve.mosquito.services;

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

    void removeTask(Task task);
}
