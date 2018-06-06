package com.softserve.mosquito.services.api;

import com.softserve.mosquito.dtos.TaskDto;

import java.util.List;

public interface TaskServiceUsingDto {
    TaskDto save(TaskDto taskDto);
    List<TaskDto> getAll();
    void remove(Long id);
}
