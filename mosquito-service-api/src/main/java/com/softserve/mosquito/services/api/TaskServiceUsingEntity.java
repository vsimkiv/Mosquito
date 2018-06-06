package com.softserve.mosquito.services.api;

import com.softserve.mosquito.entities.Priority;
import com.softserve.mosquito.entities.Status;
import com.softserve.mosquito.entities.Task;
import com.softserve.mosquito.entities.User;

import java.util.List;

public interface TaskServiceUsingEntity {

    Task create(Task task);

    Task update(Task task);

    void delete(Long id);



    Task getTaskById(Long id);

    List<Task> getAllTasks();

    List<Task> filterTasksByParent(Task parentTask);

    List<Task> filterTasksByOwner(User owner);

    List<Task> filterTasksByWorker(User worker);

    List<Task> filterTasksByPriority(Priority priority);

    List<Task> filterTasksByStatus(Status status);
}
