package com.softserve.mosquito.services.impl;

import com.softserve.mosquito.entities.Task;
import com.softserve.mosquito.repo.api.TaskRepo;
import com.softserve.mosquito.repo.impl.TaskRepoImpl;
import com.softserve.mosquito.services.api.TaskService;

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

    @Override
    public List<Task> getSubTasks(Long parentTaskId) {
        List<Task> resultTasksList = taskRepo.readAll();
        filterTasksByParent(resultTasksList, parentTaskId);
        return resultTasksList;
    }

    @Override
    public List<Task> getTasksByOwnerId(Long ownerId) {
        List<Task> resultTasksList = taskRepo.readAll();
        filterTasksByOwner(resultTasksList, ownerId);
        return resultTasksList;
    }

    @Override
    public List<Task> getTasksByWorkerId(Long workerId) {
        List<Task> resultTasksList = taskRepo.readAll();
        filterTasksByWorker(resultTasksList, workerId);
        return resultTasksList;
    }

    @Override
    public List<Task> getTasksByOwnerAndStatus(Long ownerId, Long statusId) {
        List<Task> resultTasksList = getTasksByOwnerId(ownerId);
        filterTasksByStatus(resultTasksList, statusId);
        return resultTasksList;
    }

    @Override
    public List<Task> getTasksByWorkerAndStatus(Long workerId, Long statusId) {
        List<Task> resultTasksList = getAllTasks();
        filterTasksByWorker(resultTasksList, workerId);
        filterTasksByStatus(resultTasksList, statusId);
        return resultTasksList;
    }

    @Override
    public List<Task> getTasksByParentAndOwnerAndStatus(Long parentId, Long ownerId, Long statusId) {
        List<Task> resultTasksList = taskRepo.readAll();
        filterTasksByOwner(resultTasksList, ownerId);
        filterTasksByParent(resultTasksList, parentId);
        filterTasksByStatus(resultTasksList, statusId);
        return resultTasksList;
    }

    @Override
    public List<Task> getTasksByParentAndWorkerAndStatus(Long parentId, Long workerId, Long statusId) {
        List<Task> resultTasksList = taskRepo.readAll();
        filterTasksByWorker(resultTasksList, workerId);
        filterTasksByParent(resultTasksList, parentId);
        filterTasksByStatus(resultTasksList, statusId);
        return resultTasksList;
    }


    private void filterTasksByParent(List<Task> tasks, Long parentTaskId) {
        if(parentTaskId != null) {
            tasks.removeIf((Task task) -> !task.getParentId().equals(parentTaskId));
        } else {
            tasks.removeIf((Task task) -> !task.getParentId().equals(0L));
        }
    }

    private void filterTasksByStatus(List<Task> tasks, Long statusId) {
        if(statusId != null) {
            tasks.removeIf((Task task) -> !statusId.equals(Long.valueOf(task.getStatus().getId())));
        }
    }

    private void filterTasksByWorker(List<Task> tasks, Long workerId) {
        if(workerId != null) {
            tasks.removeIf((Task task) -> !task.getWorkerId().equals(workerId));
        }
    }

    private void filterTasksByOwner(List<Task> tasks, Long ownerId) {
        if(ownerId != null) {
            tasks.removeIf((Task task) -> !task.getWorkerId().equals(ownerId));
        }
    }
}