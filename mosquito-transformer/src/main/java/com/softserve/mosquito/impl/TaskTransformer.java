package com.softserve.mosquito.impl;

import com.softserve.mosquito.api.Transformer;
import com.softserve.mosquito.dtos.TaskCreateDto;
import com.softserve.mosquito.dtos.TaskUpdateDto;
import com.softserve.mosquito.entities.Estimation;
import com.softserve.mosquito.entities.Priority;
import com.softserve.mosquito.entities.Status;
import com.softserve.mosquito.entities.Task;

public class TaskTransformer {

    public static class TaskCreate implements Transformer<Task,TaskCreateDto>{

        @Override
        public Task toEntity(TaskCreateDto taskCreateDto) {
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
        public TaskCreateDto toDTO(Task task) {
            return null;
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
