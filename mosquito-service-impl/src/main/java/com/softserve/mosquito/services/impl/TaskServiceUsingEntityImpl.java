package com.softserve.mosquito.services.impl;

import com.softserve.mosquito.dtos.TaskDto;
import com.softserve.mosquito.entities.Estimation;
import com.softserve.mosquito.entities.Task;
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

import java.util.List;

@Service
public class TaskServiceUsingEntityImpl implements TaskServiceUsingEntity {
    private TaskRepo taskRepo;

    @Autowired
    public TaskServiceUsingEntityImpl(TaskRepo taskRepo) {
        this.taskRepo = taskRepo;
    }

    @Override
    public Task createTask(Task task) {
        try {
            return taskRepo.create(task);
        } catch (DataAccessException e) {
            return new Task();
        }
    }

    @Override
    public Task updateTask(Task task) {
        return null;
    }

    @Override
    public void removeTask(Long id) {

    }

    @Override
    public List<Task> getAllTasks() {
        return null;
    }

    @Override
    public Task getTaskById(Long id) {
        return null;
    }

    @Override
    public List<Task> getTasksByOwner(Long ownerId) {
        return null;
    }

    @Override
    public List<Task> getSubTasks(Long parentId) {
        return null;
    }
}