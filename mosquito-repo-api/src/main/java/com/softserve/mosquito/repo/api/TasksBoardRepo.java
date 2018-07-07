package com.softserve.mosquito.repo.api;

import com.softserve.mosquito.entities.mongo.TasksBoard;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TasksBoardRepo extends MongoRepository<TasksBoard, String> {

    TasksBoard findByWorkerId(Long workerId);

    TasksBoard findByOwnerId(Long ownerId);
}
