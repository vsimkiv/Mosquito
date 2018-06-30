package com.softserve.mosquito.services.impl;

import com.softserve.mosquito.entities.mongo.TaskMongo;
import com.softserve.mosquito.entities.mongo.TasksBoard;
import com.softserve.mosquito.repo.api.TasksBoardRepo;
import com.softserve.mosquito.services.api.TasksBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class TasksBoardServiceImpl implements TasksBoardService {

    private TasksBoardRepo tasksBoardRepo;

    @Autowired
    public TasksBoardServiceImpl(TasksBoardRepo tasksBoardRepo) {
        this.tasksBoardRepo = tasksBoardRepo;
    }

    @Override
    public List<TaskMongo> getUserWork(Long userId) {
        TasksBoard tasksBoard = tasksBoardRepo.findByUserId(userId);
        return tasksBoard.getTaskMongos();
    }

    @Override
    public void update(TaskMongo taskMongo, Long userId) {
        TasksBoard tasksBoard = tasksBoardRepo.findByUserId(userId);
        if (tasksBoard != null) {
            List<TaskMongo> taskMongos = tasksBoard.getTaskMongos();
            for (TaskMongo taskMongoTmp : taskMongos) {
                if (taskMongoTmp.getTaskId().equals(taskMongo.getTaskId())) {
                    taskMongoTmp.setTaskName(taskMongo.getTaskName());
                    tasksBoard.setTaskMongos(taskMongos);
                    break;
                }
            }
            tasksBoardRepo.save(tasksBoard);
        }
    }

    @Override
    public void add(TaskMongo taskMongo, Long userId) {
        TasksBoard tasksBoard = tasksBoardRepo.findByUserId(userId);
        if (tasksBoard != null) {
            tasksBoard.getTaskMongos().add(taskMongo);
        } else {
            tasksBoard = new TasksBoard(userId, Arrays.asList(taskMongo));
        }
        tasksBoardRepo.save(tasksBoard);
    }

    @Override
    public void delete(TaskMongo taskMongo, Long userId) {
        TasksBoard tasksBoard = tasksBoardRepo.findByUserId(userId);
        if (tasksBoard != null) {
            tasksBoard.getTaskMongos().removeIf(taskMongoTmp -> taskMongoTmp.getTaskId().equals(taskMongo.getTaskId()));
            tasksBoardRepo.save(tasksBoard);
        }
    }
}
