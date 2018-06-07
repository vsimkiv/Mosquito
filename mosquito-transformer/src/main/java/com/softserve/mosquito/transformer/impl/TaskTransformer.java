package com.softserve.mosquito.transformer.impl;

import com.softserve.mosquito.services.api.*;
import com.softserve.mosquito.dtos.TaskDto;
import com.softserve.mosquito.entities.Task;


public class TaskTransformer {
    private static TaskServiceUsingEntity taskServiceUsingEntity;
    private static UserService userService;
    private static EstimationService estimationService;
    private static PriorityService priorityService;
    private static StatusService statusService;
    private static CommentService commentService;

    public static Task toEntity(TaskDto taskDto) {
        Task task = new Task();
        task.setId(taskDto.getId());
        task.setName(taskDto.getName());


        task.setParentTask(taskServiceUsingEntity.read(taskDto.getParentId()));

        task.setOwner(userService.getUserById(taskDto.getOwnerId()));
        task.setWorker(userService.getUserById(taskDto.getWorkerId()));

        task.setEstimation(estimationService.getEstimationById(taskDto.getEstimationId()));



        /* There is no method in PriorityService which returns object of class Priority
        Long id = taskDto.getPriorityId();
        Priority priority = priorityService.getPriorityById(id);
        task.setPriority();
        */

        /* There is no method in StatusService which returns object of class Priority
        task.setStatus(statusService.getStatusById(taskDto.getStatusId()));
        */

        task.setComments(commentService.getAllComments());
        task.setChildTasks(taskServiceUsingEntity.readAll());

        return task;
    }

    public static TaskDto toDTO(Task task) {
        TaskDto taskDto = new TaskDto();
        taskDto.setId(task.getId());
        taskDto.setName(task.getName());
        /*
         *taskDto.setParentId(task.getParentTask().getId());
         */

        taskDto.setOwnerId(task.getOwner().getId());
        taskDto.setFirstNameOfOwner(task.getOwner().getFirstName());
        taskDto.setLastNameOfOwner(task.getOwner().getLastName());

        taskDto.setWorkerId(task.getWorker().getId());
        taskDto.setFirstNameOfWorker(task.getWorker().getFirstName());
        taskDto.setLastNameOfOwner(task.getWorker().getLastName());

        taskDto.setEstimation(task.getEstimation().getTimeEstimation());
        taskDto.setRemaining(task.getEstimation().getRemaining());

        taskDto.setPriorityId(Long.valueOf(task.getPriority().getId()));
        taskDto.setPriorityTitle(task.getPriority().getTitle());

        taskDto.setStatusId(Long.valueOf(task.getStatus().getId()));
        taskDto.setStatusTitle(task.getStatus().getTitle());

        return taskDto;
    }
}
