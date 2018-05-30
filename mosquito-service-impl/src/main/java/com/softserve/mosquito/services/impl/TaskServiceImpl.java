package com.softserve.mosquito.services.impl;

import com.softserve.mosquito.api.Transformer;
import com.softserve.mosquito.dtos.TaskCreateDto;
import com.softserve.mosquito.entities.Estimation;
import com.softserve.mosquito.entities.Task;
import com.softserve.mosquito.impl.TaskTransformer;
import com.softserve.mosquito.repo.api.EstimationRepo;
import com.softserve.mosquito.repo.api.TaskRepo;
import com.softserve.mosquito.repo.impl.EstimationRepoImpl;
import com.softserve.mosquito.repo.impl.TaskRepoImpl;
import com.softserve.mosquito.services.api.TaskService;

import java.util.List;

public class TaskServiceImpl implements TaskService {

    private TaskRepo taskRepo = new TaskRepoImpl();
    private EstimationRepo estimationRepo = new EstimationRepoImpl();
    private Transformer<Task, TaskCreateDto> taskCreateTransformer = new TaskTransformer.TaskCreate();

    @Override
    public List<Task> getAllTasks() {
        return taskRepo.readAll();
    }

    @Override
    public Task getTaskById(Long id) {
        return taskRepo.read(id);
    }

    @Override
    public Task createTask(TaskCreateDto taskCreateDto) {
        Task task = taskCreateTransformer.toEntity(taskCreateDto);
        Estimation createdEstimation = estimationRepo.create(task.getEstimation());
        task.setEstimation(createdEstimation);
        return taskRepo.create(task);
    }
    @Override
    public Task updateTask(Task task) {
        return taskRepo.update(task);
    }

    @Override
    public void removeTask(Long id) {
        taskRepo.delete(id);
    }

    @Override
    public List<Task> getSubTasks(Long parentTaskId) {
        List<Task> resultTasksList = taskRepo.readAll();
        resultTasksList.removeIf((Task task) -> filterByParent(task, parentTaskId));
        return resultTasksList;
    }

    @Override
    public List<Task> getTasksByOwner(Long ownerId) {
        List<Task> resultTasksList = taskRepo.readAll();
        resultTasksList.removeIf((Task task) -> filterByOwner(task, ownerId));
        return resultTasksList;
    }

    @Override
    public List<Task> getTasksByWorker(Long workerId) {
        List<Task> resultTasksList = taskRepo.readAll();
        resultTasksList.removeIf((Task task) -> filterByWorker(task, workerId));
        return resultTasksList;
    }

    @Override
    public List<Task> getTasksByOwnerAndStatus(Long ownerId, Byte statusId) {
        List<Task> resultTasksList = getTasksByOwner(ownerId);
        resultTasksList.removeIf((Task task) -> (filterByOwner(task, ownerId) || filterByStatus(task, statusId)));
        return resultTasksList;
    }

    @Override
    public List<Task> getTasksByWorkerAndStatus(Long workerId, Byte statusId) {
        List<Task> resultTasksList = getAllTasks();
        resultTasksList.removeIf((Task task) -> (filterByWorker(task, workerId) || filterByStatus(task, statusId)));
        return resultTasksList;
    }

    @Override
    public List<Task> getTasksByOwnerAndStatusAndParent(Long parentId, Long ownerId, Byte statusId) {
        List<Task> resultTasksList = taskRepo.readAll();
        resultTasksList.removeIf((Task task) ->
                (filterByOwner(task, ownerId) || filterByStatus(task, statusId) || filterByParent(task, parentId)));
        return resultTasksList;
    }

    @Override
    public List<Task> getTasksByWorkerAndStatusAndParent(Long parentId, Long workerId, Byte statusId) {
        List<Task> resultTasksList = taskRepo.readAll();
        resultTasksList.removeIf((Task task) ->
                (filterByWorker(task, workerId) || filterByStatus(task, statusId) || filterByParent(task, parentId)));
        return resultTasksList;
    }

    private boolean filterByParent(Task task, Long parentTaskId) {
        if(parentTaskId != null) {
            if (task.getParentId().equals(parentTaskId))
                return false;
            else
                return true;
        }
        else { // parentId = null -> Get projects where parentId = 0
            if(task.getParentId().equals(0L))
                return false;
            else
                return true;
        }
    }

    private boolean filterByWorker(Task task, Long workerId) {
        if(workerId != null) {
            if (task.getWorkerId().equals(workerId))
                return false;
            else
                return true;
        }
        return false;
    }

    private boolean filterByOwner(Task task, Long ownerId) {
        if(ownerId != null) {
            if (task.getOwnerId().equals(ownerId))
                return false;
            else
                return true;
        }
        return false;
    }

    private boolean filterByStatus(Task task, Byte statusId) {
        if(statusId != null) {
            if (task.getStatus().getId().equals(statusId))
                return false;
            else
                return true;
        }
        return false;
    }
}