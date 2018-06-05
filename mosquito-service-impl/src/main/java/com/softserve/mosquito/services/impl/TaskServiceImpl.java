package com.softserve.mosquito.services.impl;

import com.softserve.mosquito.api.Transformer;
import com.softserve.mosquito.dtos.TaskDto;
import com.softserve.mosquito.entities.Estimation;
import com.softserve.mosquito.entities.Task;
import com.softserve.mosquito.entities.User;
import com.softserve.mosquito.impl.TaskTransformer;
import com.softserve.mosquito.repo.api.EstimationRepo;
import com.softserve.mosquito.repo.api.TaskRepo;
import com.softserve.mosquito.repo.impl.EstimationRepoImpl;
import com.softserve.mosquito.repo.impl.TaskRepoImpl;
import com.softserve.mosquito.services.api.TaskService;
import com.softserve.mosquito.services.api.UserService;

import java.util.List;

public class TaskServiceImpl implements TaskService {

    private TaskRepo taskRepo = new TaskRepoImpl();
    private UserService userService = new UserServiceImpl(null);
    private EstimationRepo estimationRepo = new EstimationRepoImpl();
    private Transformer<Task, TaskDto> taskDtoTransformer = new TaskTransformer.TaskDefaultDto();

    @Override
    public List<Task> getAllTasks() { return taskRepo.readAll(); }

    @Override
    public TaskDto getTaskById(Long id) {
        Task task = taskRepo.read(id);
        User assigneeUser = userService.getUserById(task.getWorkerId());

        TaskDto taskDto = taskDtoTransformer.toDTO(task);
        taskDto.setAssigneeFirstName(assigneeUser.getFirstName());
        taskDto.setAssigneeLastName(assigneeUser.getLastName());

        return taskDto;
    }

    @Override
    public Task createTask(com.softserve.mosquito.dtos.TaskDto taskCreateDto) {
        Task task = taskDtoTransformer.toEntity(taskCreateDto);
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
        resultTasksList.removeIf((Task task) -> isNotParentsTask(task, parentTaskId));
        return resultTasksList;
    }

    @Override
    public List<Task> getTasksByOwner(Long ownerId) {
        List<Task> resultTasksList = taskRepo.readAll();
        resultTasksList.removeIf((Task task) -> isNotOwnersTask(task, ownerId));
        return resultTasksList;
    }

    @Override
    public List<Task> getTasksByWorker(Long workerId) {
        List<Task> resultTasksList = taskRepo.readAll();
        resultTasksList.removeIf((Task task) -> isNotWorkersTask(task, workerId));
        return resultTasksList;
    }

    @Override
    public List<Task> getTasksByOwnerAndStatus(Long ownerId, Byte statusId) {
        List<Task> resultTasksList = getTasksByOwner(ownerId);
        resultTasksList.removeIf((Task task) -> (isNotOwnersTask(task, ownerId) || isNotTaskOfStatus(task, statusId)));
        return resultTasksList;
    }

    @Override
    public List<Task> getTasksByWorkerAndStatus(Long workerId, Byte statusId) {
        List<Task> resultTasksList = getAllTasks();
        resultTasksList.removeIf((Task task) -> (isNotWorkersTask(task, workerId) || isNotTaskOfStatus(task, statusId)));
        return resultTasksList;
    }

    @Override
    public List<Task> getTasksByOwnerAndStatusAndParent(Long parentId, Long ownerId, Byte statusId) {
        List<Task> resultTasksList = taskRepo.readAll();
        resultTasksList.removeIf((Task task) ->
                (isNotOwnersTask(task, ownerId) || isNotTaskOfStatus(task, statusId) || isNotParentsTask(task, parentId)));
        return resultTasksList;
    }

    @Override
    public List<Task> getTasksByWorkerAndStatusAndParent(Long parentId, Long workerId, Byte statusId) {
        List<Task> resultTasksList = taskRepo.readAll();
        resultTasksList.removeIf((Task task) ->
                (isNotWorkersTask(task, workerId) || isNotTaskOfStatus(task, statusId) || isNotParentsTask(task, parentId)));
        return resultTasksList;
    }

    private boolean isNotParentsTask(Task task, Long parentTaskId) {
        if(parentTaskId != null) {
            return !task.getParentId().equals(parentTaskId);
        }
        else { // parentId = null -> Get projects where parentId = 0
            return !task.getParentId().equals(0L);
        }
    }

    private boolean isNotWorkersTask(Task task, Long workerId) {
        if(workerId != null) {
            return !task.getWorkerId().equals(workerId);
        }
        return false;
    }

    private boolean isNotOwnersTask(Task task, Long ownerId) {
        if(ownerId != null) {
            return !task.getOwnerId().equals(ownerId);
        }
        return false;
    }

    private boolean isNotTaskOfStatus(Task task, Byte statusId) {
        if(statusId != null) {
            return !task.getStatus().getId().equals(statusId);
        }
        return false;
    }
}