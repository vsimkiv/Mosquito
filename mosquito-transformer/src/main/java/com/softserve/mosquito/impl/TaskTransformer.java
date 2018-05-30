package com.softserve.mosquito.impl;

import com.softserve.mosquito.api.Transformer;
import com.softserve.mosquito.dtos.TaskCreateDto;
import com.softserve.mosquito.dtos.TaskUpdateDto;
import com.softserve.mosquito.entities.Task;

public class TaskTransformer {

    public static class TaskCreate implements Transformer<Task,TaskCreateDto>{

        @Override
        public Task toEntity(TaskCreateDto taskCreateDto) {
            return null;
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
