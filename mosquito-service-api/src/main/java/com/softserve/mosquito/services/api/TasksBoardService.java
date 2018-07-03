package com.softserve.mosquito.services.api;

import com.softserve.mosquito.entities.mongo.TaskMongo;

import java.util.List;

public interface TasksBoardService {
    List<TaskMongo> getUserWork(Long userId);

    List<TaskMongo> getUserTask(Long ownerId);

    void update(TaskMongo taskMongo, Long userId);

    void add(TaskMongo taskMongo, Long ownerId, Long userId);

}
