package com.softserve.mosquito.repository;

import com.softserve.mosquito.api.Transformer;
import com.softserve.mosquito.dtos.PriorityCreateDto;
import com.softserve.mosquito.entities.Priority;

public class PriorityTransformer {

    static class PriorityCreate implements Transformer<Priority,PriorityCreateDto>{

        @Override
        public Priority toEntity(PriorityCreateDto priorityCreateDto) {
            return new Priority(priorityCreateDto.getTitle());
        }

        @Override
        public PriorityCreateDto toDTO(Priority priority) {
            return new PriorityCreateDto(priority.getTitle());
        }
    }

}
