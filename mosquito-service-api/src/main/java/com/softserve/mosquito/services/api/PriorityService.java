package com.softserve.mosquito.services.api;

import com.softserve.mosquito.dtos.PriorityCreateDto;
import com.softserve.mosquito.dtos.PriorityDto;
import com.softserve.mosquito.entities.Priority;

import java.util.List;

public interface PriorityService {

    List<PriorityDto> getAll();

    PriorityDto getById(Long id);

    PriorityDto save(PriorityCreateDto priorityCreateDto);

    PriorityDto update(PriorityDto priorityDto);

    void delete(Long id);

    /**
     * method for using in class TaskTransformer
     */
    Priority getPriorityEntityById(Long id);
}
