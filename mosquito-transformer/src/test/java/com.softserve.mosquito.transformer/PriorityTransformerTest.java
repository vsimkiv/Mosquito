package com.softserve.mosquito.transformer;

import com.softserve.mosquito.dtos.PriorityDto;
import com.softserve.mosquito.entities.Priority;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class PriorityTransformerTest {

    @Test
    public void toEntity() {
        PriorityDto priorityDto = new PriorityDto();
        priorityDto.setTitle("ForToday");
        Priority priority = PriorityTransformer.toEntity(priorityDto);
        assertEquals(priorityDto.getId(), priority.getId());
        assertEquals(priorityDto.getTitle(), priority.getTitle());
    }

    @Test
    public void toDTO() {
        Priority priority = new Priority();
        priority.setTitle("ForTomorrow");
        PriorityDto priorityDto = PriorityTransformer.toDTO(priority);
        assertEquals(priority.getId(), priorityDto.getId());
        assertEquals(priority.getTitle(), priorityDto.getTitle());
    }

    @Test
    public void toEntityList() {
        PriorityDto priorityDto1 = new PriorityDto();
        priorityDto1.setTitle("TestPriority");
        PriorityDto priorityDto2 = new PriorityDto();
        priorityDto2.setTitle("TestPriority2");
        List<PriorityDto> priorityDtos = new ArrayList<>();
        priorityDtos.add(priorityDto1);
        priorityDtos.add(priorityDto2);
        List<Priority> priorities = PriorityTransformer.toEntityList(priorityDtos);
        Priority firstPriority = priorities.stream().findFirst().get();
        assertEquals(priorityDto1.getId(), firstPriority.getId());
    }

    @Test
   public void toDTOList() {
        Priority priority1 = new Priority();
        priority1.setTitle("Test");
        Priority priority2 = new Priority();
        priority2.setTitle("TestTwo");
        List<Priority> priorities = new ArrayList<>();
        priorities.add(priority1);
        priorities.add(priority2);
        List<PriorityDto> priorityDtos = PriorityTransformer.toDTOList(priorities);
        PriorityDto firstDto = priorityDtos.stream().findFirst().get();
        assertEquals(priority1.getTitle(), firstDto.getTitle());
    }
    @Test
    public void toEntity_null() {
        PriorityDto priorityDto = null;
        Priority priority = PriorityTransformer.toEntity(priorityDto);
        assertNull(priority);
    }
    @Test
    public void toDTO_null() {
        Priority priority = null;
        PriorityDto priorityDto = PriorityTransformer.toDTO(priority);
        assertNull(priorityDto);
    }
}