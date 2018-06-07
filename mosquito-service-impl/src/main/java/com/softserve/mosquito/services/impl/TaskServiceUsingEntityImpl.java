package com.softserve.mosquito.services.impl;

import com.softserve.mosquito.entities.Task;
import com.softserve.mosquito.repo.api.TaskRepo;
import com.softserve.mosquito.services.api.TaskServiceUsingEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class TaskServiceUsingEntityImpl implements TaskServiceUsingEntity {
    private TaskRepo taskRepo;

    @Autowired
    public TaskServiceUsingEntityImpl(TaskRepo taskRepo) {
        this.taskRepo = taskRepo;
    }

    @Transactional
    @Override
    public Task create(Task task) {
        try {
            return taskRepo.create(task);
        } catch (DataAccessException e) {
            return new Task();
        }
    }

    @Transactional
    @Override
    public Task read(Long id) {
        return taskRepo.read(id);
    }

    @Transactional
    @Override
    public List<Task> readAll() {
        return taskRepo.readAll();
    }
}