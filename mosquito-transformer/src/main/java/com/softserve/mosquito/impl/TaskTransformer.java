package com.softserve.mosquito.impl;

import com.softserve.mosquito.api.Transformer;
import com.softserve.mosquito.dtos.TaskDto;
import com.softserve.mosquito.dtos.TaskUpdateDto;
import com.softserve.mosquito.entities.Estimation;
import com.softserve.mosquito.entities.Priority;
import com.softserve.mosquito.entities.Status;
import com.softserve.mosquito.entities.Task;

public class TaskTransformer {

    private TaskTransformer() {
        throw new IllegalStateException("Utility class");
    }

    public static class TaskDefaultDto implements Transformer<Task, TaskDto>{

        @Override
        public Task toEntity(TaskDto taskCreateDto) {
            Task task = new Task();
            task.setName(taskCreateDto.getName());
            task.setEstimation(new Estimation(taskCreateDto.getEstimation()));
            task.setOwnerId(taskCreateDto.getOwnerId());
            task.setWorkerId(taskCreateDto.getWorkerId());
            task.setParentId(taskCreateDto.getParentId());
            task.setStatus(new Status(taskCreateDto.getStatusId()));
            task.setPriority(new Priority(taskCreateDto.getPriorityId()));
            return task;
        }

        @Override
        public TaskDto toDTO(Task task) {
            TaskDto taskCreateDto = new TaskDto();
            taskCreateDto.setName(task.getName());
            taskCreateDto.setEstimation(task.getEstimation().getEstimation());
            taskCreateDto.setRemaining(task.getEstimation().getRemaining());
            taskCreateDto.setOwnerId(task.getOwnerId());
            taskCreateDto.setWorkerId(task.getWorkerId());
            taskCreateDto.setParentId(task.getParentId());
            taskCreateDto.setPriorityId(task.getPriority().getId());
            taskCreateDto.setStatusId(task.getStatus().getId());
            taskCreateDto.setStatusTitle(task.getStatus().getTitle());
            taskCreateDto.setPriorityTitle(task.getPriority().getTitle());
            return taskCreateDto;
        }
    }

    static class TaskUpdate implements Transformer<Task,TaskUpdateDto>{

        @Override
        public Task toEntity(TaskUpdateDto taskUpdateDto) {
            return null;
        }

        @Override
        public TaskUpdateDto toDTO(Task task) {
            return null;
        }
    }

}
