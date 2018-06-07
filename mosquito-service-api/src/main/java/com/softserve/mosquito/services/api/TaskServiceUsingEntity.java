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



    Task read(Long id);

    List<Task> readAll();

    List<Task> filterByParent(Task parentTask);

    List<Task> filterByOwner(User owner);

    List<Task> filterByWorker(User worker);

    List<Task> filterByPriority(Priority priority);

    List<Task> filterByStatus(Status status);
}
