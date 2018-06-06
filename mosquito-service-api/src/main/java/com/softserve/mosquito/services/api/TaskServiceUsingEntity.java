package com.softserve.mosquito.services.api;

import com.softserve.mosquito.entities.Task;

import java.util.List;

public interface TaskServiceUsingEntity {

    Task createTask(Task task);

    Task updateTask(Task task);

    void removeTask(Long id);


    List<Task> getAllTasks();

    Task getTaskById(Long id);

    List<Task> getTasksByOwner(Long ownerId);

    List<Task> getSubTasks(Long parentId);
}
