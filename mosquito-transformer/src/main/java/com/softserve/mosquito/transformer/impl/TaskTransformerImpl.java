package com.softserve.mosquito.transformer.impl;

import com.softserve.mosquito.transformer.api.TaskTransformer;
import com.softserve.mosquito.transformer.api.Transformer;
import com.softserve.mosquito.dtos.TaskDto;
import com.softserve.mosquito.entities.Task;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class TaskTransformerImpl implements TaskTransformer {


    private TaskTransformerImpl() {
        throw new IllegalStateException("Utility class");
    }

    @Override
    public Task toEntity(TaskDto taskDto) {
        Task task = new Task();
        task.setId(taskDto.getId());
        task.setName(taskDto.getName());
        return task;
    }

    @Override
    public TaskDto toDTO(Task task) {
        TaskDto taskDto = new TaskDto();
        taskDto.setId(task.getId());
        taskDto.setName(task.getName());
        taskDto.setParentId(task.getParentTask().getId());

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


    //   public static class TaskDefaultDto implements Transformer<Task, TaskDto> {

//        @Override
//        public Task toEntity(TaskDto taskCreateDto) {
//            throw new NotImplementedException();
//
//            /*Task task = new Task();
//            task.setName(taskCreateDto.getName());
//            task.setEstimation(new Estimation(taskCreateDto.getEstimation()));
//            task.setOwnerId(taskCreateDto.getOwnerId());
//            task.setWorkerId(taskCreateDto.getWorkerId());
//            task.setParentId(taskCreateDto.getParentId());
//            task.setStatus(new Status(taskCreateDto.getStatusId()));
//            task.setPriority(new Priority(taskCreateDto.getPriorityId()));
//            return task;*/
//        }

}
