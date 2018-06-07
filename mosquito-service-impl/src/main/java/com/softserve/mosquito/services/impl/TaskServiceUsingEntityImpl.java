package com.softserve.mosquito.services.impl;

import com.softserve.mosquito.dtos.TaskDto;
import com.softserve.mosquito.entities.*;
import com.softserve.mosquito.repo.api.EstimationRepo;
import com.softserve.mosquito.repo.api.TaskRepo;
import com.softserve.mosquito.repo.impl.EstimationRepoImpl;
import com.softserve.mosquito.repo.impl.TaskRepoImpl;
import com.softserve.mosquito.services.api.TaskServiceUsingEntity;
import com.softserve.mosquito.services.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

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
    public Task update(Task task) {
        return null;
    }

    @Transactional
    @Override
    public void delete(Long id) {

    }

    @Transactional
    @Override
    public Task read(Long id) {
        return null;
    }

    @Transactional
    @Override
    public List<Task> readAll() {
        return null;
    }

    @Transactional
    @Override
    public List<Task> filterByParent(Task parentTask) {
        return null;
    }

    @Transactional
    @Override
    public List<Task> filterByOwner(User owner) {
        return null;
    }

    @Transactional
    @Override
    public List<Task> filterByWorker(User worker) {
        return null;
    }

    @Transactional
    @Override
    public List<Task> filterByPriority(Priority priority) {
        return null;
    }

    @Transactional
    @Override
    public List<Task> filterByStatus(Status status) {
        return null;
    }

}