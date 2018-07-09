package com.softserve.mosquito.services.api;

import com.softserve.mosquito.entities.mongo.TaskMongo;
import com.softserve.mosquito.entities.mongo.TasksBoard;

import java.util.List;

public interface TasksBoardService {

    List<TaskMongo> getUserWork(Long userId);

    List<TasksBoard> getByStatusId(Long statusId);

    void update(TaskMongo taskMongo, Long userId);

    void add(TaskMongo taskMongo, Long userId);

    void delete(Long id);

    void migrateDbData();

}
