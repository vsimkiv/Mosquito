package com.softserve.mosquito.transformer.impl;

import com.softserve.mosquito.dtos.*;
import com.softserve.mosquito.entities.Task;

import java.util.ArrayList;
import java.util.List;


public class TaskTransformer {
    private TaskTransformer() {
    }

    //TODO parentTask, comments, and childTasks will add on service-impl module
    public static Task toEntity(TaskDto taskDto) {
        return Task.builder()
                .id(taskDto.getId())
                .name(taskDto.getName())
                .owner(new UserTransformer().toEntity(taskDto.getOwnerDto()))
                .worker(new UserTransformer().toEntity(taskDto.getWorkerDto()))
                .estimation(EstimationTransformer.toEntity(taskDto.getEstimationDto()))
                .priority(new PriorityTransformer.PriorityGeneric().toEntity(taskDto.getPriorityDto()))
                .status(new StatusTransformer().toEntity(taskDto.getStatusDto()))
                .build();
    }

    //TODO parentTask, comments, and childTasks will add on service-impl module
    public static TaskDto toDTO(Task task) {
        return new TaskDto().builder()
                .id(task.getId())
                .name(task.getName())
                .ownerDto(new UserTransformer().toDTO(task.getOwner()))
                .workerDto(new UserTransformer().toDTO(task.getOwner()))
                .estimationDto(EstimationTransformer.toDTO(task.getEstimation()))
                .priorityDto(new PriorityTransformer.PriorityGeneric().toDTO(task.getPriority()))
                .statusDto(new StatusTransformer().toDTO(task.getStatus()))
                .build();
    }


    public static List<Task> toEntityList(List<TaskDto> taskDtoList) {
        List<Task> tasks = new ArrayList<>();
        for (TaskDto taskDto : taskDtoList) {
            tasks.add(toEntity(taskDto));
        }
        return tasks;
    }

    public static List<TaskDto> toDTOList(List<Task> tasks) {
        List<TaskDto> taskDtoList = new ArrayList<>();
        for (Task task : tasks) {
            taskDtoList.add(toDTO(task));
        }
        return taskDtoList;
    }
}
