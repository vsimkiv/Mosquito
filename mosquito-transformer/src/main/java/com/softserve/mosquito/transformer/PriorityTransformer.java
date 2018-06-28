package com.softserve.mosquito.transformer;

import com.softserve.mosquito.dtos.PriorityDto;
import com.softserve.mosquito.entities.Priority;

import java.util.ArrayList;
import java.util.List;

public class PriorityTransformer {

    private PriorityTransformer() {
    }

    public static Priority toEntity(PriorityDto priorityDto) {
        if (priorityDto==null) return null;
        return new Priority(priorityDto.getId(), priorityDto.getTitle());
    }

    public static PriorityDto toDTO(Priority priority) {
        if (priority==null) return null;
        return new PriorityDto(priority.getId(), priority.getTitle());
    }

    public static List<Priority> toEntityList(List<PriorityDto> priorityDtos) {
        List<Priority> priorities = new ArrayList<>();

        if(priorityDtos != null && !priorityDtos.isEmpty()) {
            for (PriorityDto priorityDto : priorityDtos) {
                priorities.add(toEntity(priorityDto));
            }
        }
        return priorities;
    }

    public static List<PriorityDto> toDTOList(List<Priority> priorities){
        List<PriorityDto> priorityDtos = new ArrayList<>();

        if(priorities != null && !priorities.isEmpty()) {
            for (Priority priority : priorities) {
                priorityDtos.add(toDTO(priority));
            }
        }
        return priorityDtos;
    }

}