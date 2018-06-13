package com.softserve.mosquito.services.api;

import com.softserve.mosquito.entities.mongo.Task;

import java.util.List;

public interface TasksBoardService {
    List<Task> getUserWork(Long userId);
    void update(Task task, Long userId);
    void add(Task task, Long userId);
    void delete(Task task, Long userId);
}
