package com.softserve.mosquito.impl;

import com.softserve.mosquito.api.Transformer;
import com.softserve.mosquito.dtos.PriorityCreateDto;
import com.softserve.mosquito.dtos.PriorityDto;
import com.softserve.mosquito.entities.Priority;

public class PriorityTransformer {

    private PriorityTransformer() {
        throw new IllegalStateException("Utility class");
    }

    public static class PriorityCreate implements Transformer<Priority,PriorityCreateDto>{

        @Override
        public Priority toEntity(PriorityCreateDto priorityCreateDto) {
            return new Priority(priorityCreateDto.getTitle());
        }

        @Override
        public PriorityCreateDto toDTO(Priority priority) {
            return new PriorityCreateDto(priority.getTitle());
        }
    }

    public static class PriorityGeneric implements Transformer<Priority,PriorityDto>{

        @Override
        public Priority toEntity(PriorityDto priorityDto) {
            return new Priority(priorityDto.getId(),
                    priorityDto.getTitle());
        }

        @Override
        public PriorityDto toDTO(Priority priority) {
            return new PriorityDto(priority.getId(),
                    priority.getTitle());
        }
    }

}
