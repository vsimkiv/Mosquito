package com.softserve.mosquito.services.impl;

import com.softserve.mosquito.dtos.TaskDto;
import com.softserve.mosquito.services.api.TaskServiceUsingDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskServiceUsingDtoImpl implements TaskServiceUsingDto {

    @Override
    public TaskDto save(TaskDto taskDto) {
        return taskDto;
    }

    @Override
    public List<TaskDto> getAll() {
        return new ArrayList<>();
    }
}
