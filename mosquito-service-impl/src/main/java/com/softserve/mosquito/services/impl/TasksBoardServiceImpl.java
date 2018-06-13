package com.softserve.mosquito.services.impl;

import com.softserve.mosquito.entities.mongo.Task;
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
    public List<Task> getUserWork(Long userId) {
        TasksBoard tasksBoard = tasksBoardRepo.findByUserId(userId);
        return tasksBoard.getTasks();
    }

    @Override
    public void update(Task task, Long userId) {
        TasksBoard tasksBoard = tasksBoardRepo.findByUserId(userId);
        if(tasksBoard != null) {
            List<Task> tasks = tasksBoard.getTasks();
            for (Task taskTmp : tasks) {
                if(taskTmp.getTaskId().equals(task.getTaskId())) {
                    taskTmp.setTaskName(task.getTaskName());
                    tasksBoard.setTasks(tasks);
                    break;
                }
            }
            tasksBoardRepo.save(tasksBoard);
        }
    }

    @Override
    public void add(Task task, Long userId) {
        TasksBoard tasksBoard = tasksBoardRepo.findByUserId(userId);
        if(tasksBoard != null) {
            tasksBoard.getTasks().add(task);
        }
        else {
            tasksBoard = new TasksBoard(userId, Arrays.asList(task));
        }
        tasksBoardRepo.save(tasksBoard);
    }

    @Override
    public void delete(Task task, Long userId) {
        TasksBoard tasksBoard = tasksBoardRepo.findByUserId(userId);
        if(tasksBoard != null) {
            tasksBoard.getTasks().removeIf(taskTmp -> taskTmp.getTaskId().equals(task.getTaskId()));
            tasksBoardRepo.save(tasksBoard);
        }
    }
}
