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
        Task task = Task.builder()
                .id(taskDto.getId())
                .name(taskDto.getName())
                .parentTask(taskServiceUsingEntity.read(taskDto.getParentId()))
                .owner(userService.getUserById(taskDto.getOwnerId()))
                .worker(userService.getUserById(taskDto.getWorkerId()))
                .estimation(estimationService.getEstimationById(taskDto.getEstimationId()))
                .priority(priorityService.getPriorityEntityById(taskDto.getPriorityId()))
                .status(statusService.getStatusEntityById(taskDto.getStatusId()))

                .comments(commentService.getAllComments())
                .childTasks(taskServiceUsingEntity.readAll())
                .build();

        return task;
    }

    public static TaskDto toDTO(Task task) {
        return new TaskDto().builder()
                .id(task.getId())
                .name(task.getName())
                //.parentId(task.getParentTask().getId())

                .ownerId(task.getOwner().getId())
                .firstNameOfOwner(task.getOwner().getFirstName())
                .lastNameOfOwner(task.getOwner().getLastName())

                .workerId(task.getWorker().getId())
                .firstNameOfWorker(task.getWorker().getFirstName())
                .lastNameOfWorker(task.getWorker().getLastName())

                .estimationId(task.getEstimation().getId())
                .estimation(task.getEstimation().getTimeEstimation())
                .remaining(task.getEstimation().getRemaining())

                .priorityId(Long.valueOf(task.getPriority().getId()))
                .priorityTitle(task.getPriority().getTitle())
                .statusId(Long.valueOf(task.getStatus().getId()))
                .statusTitle(task.getStatus().getTitle())
                .build();
    }
}
