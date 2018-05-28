package com.softserve.mosquito.services;

import com.softserve.mosquito.entities.Task;
import com.softserve.mosquito.repo.api.TaskRepo;
import com.softserve.mosquito.repo.impl.TaskRepoImpl;

import java.util.Iterator;
import java.util.List;

public class TaskServiceImpl implements TaskService {

    private TaskRepo taskRepo = new TaskRepoImpl();

    @Override
    public List<Task> getAllTasks() {
        return taskRepo.readAll();
    }

    @Override
    public Task getTaskById(Long id) {
        return taskRepo.read(id);
    }

    @Override
    public List<Task> getTasksByOwnerId(Long ownerId) {
        List<Task> tasks = taskRepo.readAll();
        tasks.removeIf((Task task) -> !task.getOwnerId().equals(ownerId));
        return tasks;
    }

    @Override
    public List<Task> getTasksByWorkerId(Long workerId) {
        List<Task> tasks = taskRepo.readAll();
        tasks.removeIf((Task task) -> !task.getWorkerId().equals(workerId));
        return tasks;
    }

    @Override
    public List<Task> getSubTasks(Long parentTaskId) {
        List<Task> tasks = taskRepo.readAll();
        tasks.removeIf((Task task) -> !task.getParentId().equals(parentTaskId));
        return tasks;
    }

    @Override
    public Task createTask(Task task) {
        return taskRepo.create(task);
    }

    @Override
    public Task updateTask(Task task) {
        return taskRepo.update(task);
    }

    @Override
    public void removeTask(Task task) {
        taskRepo.delete(task);
    }
}
