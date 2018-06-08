package com.softserve.mosquito.services.api;

import com.softserve.mosquito.dtos.PriorityCreateDto;
import com.softserve.mosquito.dtos.PriorityDto;
import com.softserve.mosquito.entities.Priority;

import java.util.List;

public interface PriorityService {
    List<PriorityDto> getAllPriorities();

    PriorityDto getPriorityById(Long id);

    PriorityDto createPriority(PriorityCreateDto priorityCreateDto);

    PriorityDto updatePriority(PriorityDto priorityDto);

    void removePriority(Long id);

    /**
     * method for using in class TaskTransformer
     */
    Priority getPriorityEntityById(Long id);
}
