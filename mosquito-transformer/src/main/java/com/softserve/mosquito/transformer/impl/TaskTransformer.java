package com.softserve.mosquito.transformer.impl;

import com.softserve.mosquito.dtos.TaskDto;
import com.softserve.mosquito.entities.Task;

import java.util.ArrayList;
import java.util.List;


public class TaskTransformer {
    private TaskTransformer() {
    }

    //parentTask, comments, and childTasks will set on service-impl module
    public static Task toEntity(TaskDto taskDto) {
        return Task.builder()
                .id(taskDto.getId())
                .name(taskDto.getName())
                .owner(UserTransformer.toEntity(taskDto.getOwnerDto()))
                .worker(UserTransformer.toEntity(taskDto.getWorkerDto()))
                .estimation(EstimationTransformer.toEntity(taskDto.getEstimationDto()))
                .priority(PriorityTransformer.toEntity(taskDto.getPriorityDto()))
                .status(StatusTransformer.toEntity(taskDto.getStatusDto()))
                .build();
    }

    //parentTask, comments, and childTasks will set on service-impl module
    public static TaskDto toDTO(Task task) {
        return new TaskDto().builder()
                .id(task.getId())
                .name(task.getName())
                .ownerDto(UserTransformer.toDTO(task.getOwner()))
                .workerDto(UserTransformer.toDTO(task.getOwner()))
                .estimationDto(EstimationTransformer.toDTO(task.getEstimation()))
                .priorityDto(PriorityTransformer.toDTO(task.getPriority()))
                .statusDto(StatusTransformer.toDTO(task.getStatus()))
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
