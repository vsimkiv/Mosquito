package com.softserve.mosquito.services.impl;

import com.mongodb.BasicDBObject;
import com.softserve.mosquito.entities.Task;
import com.softserve.mosquito.entities.mongo.TaskMongo;
import com.softserve.mosquito.entities.mongo.TasksBoard;
import com.softserve.mosquito.repo.api.TasksBoardRepo;
import com.softserve.mosquito.services.api.TasksBoardService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class TasksBoardServiceImpl implements TasksBoardService {

    private TasksBoardRepo tasksBoardRepo;
    private MongoOperations mongoOperations;
    private SessionFactory sessionFactory;

    @Autowired
    public TasksBoardServiceImpl(TasksBoardRepo tasksBoardRepo, MongoOperations mongoOperations, SessionFactory sessionFactory) {
        this.tasksBoardRepo = tasksBoardRepo;
        this.mongoOperations = mongoOperations;
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<TaskMongo> getUserWork(Long userId) {
        TasksBoard tasksBoard = tasksBoardRepo.findByWorkerId(userId);
        return tasksBoard == null ? Collections.emptyList() : tasksBoard.getTaskMongos();
    }

    @Override
    public List<TasksBoard> getByStatusId(Long statusId) {
        Query query = new Query(
                Criteria.where("taskMongos.statusId").is(statusId)
        );
        List<TasksBoard> mongos = mongoOperations.find(query,TasksBoard.class);
        System.out.println(mongos);
        return mongos;
    }

    @Override
    public void update(TaskMongo taskMongo, Long workerId) {
        Query query = new Query(Criteria.where("taskMongos.taskId").is(taskMongo.getTaskId()));
        mongoOperations.updateFirst(query,
                new Update().set("taskMongos.$.taskName", taskMongo.getTaskName()), TasksBoard.class);
    }

    @Override
    public void add(TaskMongo taskMongo, Long workerId) {
        TasksBoard tasksBoard = tasksBoardRepo.findByWorkerId(workerId);
        if (tasksBoard != null) {
            tasksBoard.getTaskMongos().add(taskMongo);
        } else
            tasksBoard = new TasksBoard(workerId, Arrays.asList(taskMongo));

        mongoOperations.save(tasksBoard);
    }

    @Override
    public void delete(Long id) {
        Query query = new Query(
                Criteria.where("taskMongos.taskId").is(id)
        );
        mongoOperations.updateFirst(query, new Update().pull("taskMongos", new BasicDBObject("taskId", id)), TasksBoard.class);
    }

    public void migrateDbData() {
        try (Session session = sessionFactory.openSession()) {
            List<Task> tasks = session.createQuery("FROM " + Task.class.getName(), Task.class).getResultList();
            for (Task task : tasks) {
                add(new TaskMongo(task.getId(), task.getName(), task.getStatus().getId()),
                        task.getWorker().getId());
            }
        }
    }

}
