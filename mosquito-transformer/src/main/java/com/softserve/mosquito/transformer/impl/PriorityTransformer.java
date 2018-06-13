package com.softserve.mosquito.transformer.impl;

import com.softserve.mosquito.dtos.PriorityCreateDto;
import com.softserve.mosquito.dtos.PriorityDto;
import com.softserve.mosquito.entities.Priority;

public class PriorityTransformer {

    private PriorityTransformer() {
        throw new IllegalStateException("Utility class");
    }

    public static Priority toEntity(PriorityCreateDto priorityCreateDto) {
        return new Priority(priorityCreateDto.getTitle());
    }

    public static PriorityCreateDto toDTO(Priority priority) {
        return new PriorityCreateDto(priority.getTitle());
    }

    public static Priority toEntity(PriorityDto priorityDto) {
        return new Priority(priorityDto.getId(),
                priorityDto.getTitle());
    }

}