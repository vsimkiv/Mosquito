package com.softserve.mosquito.transformer;

import com.softserve.mosquito.dtos.PriorityDto;
import com.softserve.mosquito.entities.Priority;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PriorityTransformerTest {

    @Test
    void toEntity() {
        PriorityDto priorityDto = new PriorityDto();
        priorityDto.setId(10L);
        priorityDto.setTitle("ForToday");
        Priority priority = new Priority();
        priority.setId(priorityDto.getId());
        priority.setTitle(priorityDto.getTitle());
        assertEquals(priorityDto.getId(), priority.getId());
        assertEquals(priorityDto.getTitle(), priority.getTitle());
    }

    @Test
    void toDTO() {
        Priority priority = new Priority();
        priority.setId(20L);
        priority.setTitle("ForTomorrow");
        PriorityDto priorityDto = new PriorityDto();
        priorityDto.setId(priority.getId());
        priorityDto.setTitle(priority.getTitle());
        assertEquals(priority.getId(), priorityDto.getId());
        assertEquals(priority.getTitle(), priorityDto.getTitle());
    }

    @Test
    void toEntityList() {
        PriorityDto priorityDto1 = new PriorityDto();
        priorityDto1.setId(25L);
        priorityDto1.setTitle("TestPriority");
        PriorityDto priorityDto2 = new PriorityDto();
        priorityDto2.setId(30L);
        priorityDto2.setTitle("TestPriority2");
        List<PriorityDto> priorityDtos = new ArrayList<>();
        priorityDtos.add(priorityDto1);
        priorityDtos.add(priorityDto2);
        List<Priority> priorities = new ArrayList<>();
        for (PriorityDto priorityDtoo:priorityDtos) {
            priorities.add(PriorityTransformer.toEntity(priorityDtoo));
        }
    }

    @Test
    void toDTOList() {
        Priority priority1 = new Priority();
        priority1.setId(40L);
        priority1.setTitle("Test");
        Priority priority2 = new Priority();
        priority2.setId(50L);
        priority2.setTitle("TestTwo");
        List<Priority> priorities = new ArrayList<>();
        priorities.add(priority1);
        priorities.add(priority2);
        List<PriorityDto> priorityDtos = new ArrayList<>();
        for (Priority priorityy: priorities) {
            priorityDtos.add(PriorityTransformer.toDTO(priorityy));
        }
    }
}