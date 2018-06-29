package com.softserve.mosquito.transformer;

import com.softserve.mosquito.dtos.TaskFullDto;
import com.softserve.mosquito.dtos.TaskSimpleDto;
import com.softserve.mosquito.entities.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskTransformer {

    private TaskTransformer() {
    }

    //parentTask, comments, and childTasks will set on service-impl module
    public static Task toEntity(TaskFullDto taskFullDto) {
        if (taskFullDto == null) {
            return null;
        } else
            return Task.builder()
                    .id(taskFullDto.getId())
                    .name(taskFullDto.getName())
                    .owner(UserTransformer.toEntity(taskFullDto.getOwnerDto()))
                    .worker(UserTransformer.toEntity(taskFullDto.getWorkerDto()))
                    .estimation(EstimationTransformer.toEntity(taskFullDto.getEstimationDto()))
                    .priority(PriorityTransformer.toEntity(taskFullDto.getPriorityDto()))
                    .status(StatusTransformer.toEntity(taskFullDto.getStatusDto()))
                    .build();
    }

    //parentTask, estimation, comments, and childTasks will set on service-impl module
    public static TaskFullDto toFullDTO(Task task) {
        if (task == null) {
            return null;
        } else
            return TaskFullDto.builder()
                    .id(task.getId())
                    .name(task.getName())
                    .ownerDto(UserTransformer.toDTO(task.getOwner()))
                    .workerDto(UserTransformer.toDTO(task.getOwner()))
                    .priorityDto(PriorityTransformer.toDTO(task.getPriority()))
                    .statusDto(StatusTransformer.toDTO(task.getStatus()))
                    .build();
    }

    public static TaskSimpleDto toSimpleDto(Task task) {
        if (task == null) {
            return null;
        } else
            return new TaskSimpleDto(
                    task.getId(),
                    task.getName(),
                    task.getParentTask() != null ? task.getParentTask().getName() : null,
                    task.getOwner().getId().toString(),
                    task.getWorker().getId().toString(),
                    task.getEstimation().getTimeEstimation().toString(),
                    task.getPriority().getTitle(),
                    task.getStatus().getTitle());
    }


    public static List<Task> toEntityList(List<TaskFullDto> taskFullDtoList) {
        List<Task> tasks = new ArrayList<>();
        for (TaskFullDto taskFullDto : taskFullDtoList) {
            tasks.add(toEntity(taskFullDto));
        }
        return tasks;
    }

    public static List<TaskFullDto> toDTOList(List<Task> tasks) {
        List<TaskFullDto> taskFullDtoList = new ArrayList<>();
        if (tasks != null && !tasks.isEmpty())
            for (Task task : tasks) {
                taskFullDtoList.add(toFullDTO(task));
            }
        return taskFullDtoList;
    }
}
