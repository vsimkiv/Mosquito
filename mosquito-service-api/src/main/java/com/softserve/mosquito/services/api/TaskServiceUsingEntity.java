package com.softserve.mosquito.services.api;

import com.softserve.mosquito.entities.Task;

import java.util.List;

/*
 * Required by TaskTransformer
 */
public interface TaskServiceUsingEntity {

    Task create(Task task);

    Task read(Long id);

    List<Task> readAll();
}
