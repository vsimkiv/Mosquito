package com.softserve.mosquito.transformer;

import com.softserve.mosquito.dtos.StatusDto;
import com.softserve.mosquito.entities.Status;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;


public class StatusTransformerTest {

    @Test
    public void toEntity() {
        StatusDto statusDto = new StatusDto();
        statusDto.setId(20L);
        statusDto.setTitle("Cancelled");

        Status status = StatusTransformer.toEntity(statusDto);
        assertEquals(statusDto.getId(), status.getId());
        assertEquals(statusDto.getTitle(), status.getTitle());
    }

    @Test
    public void toEntity_null() {
        StatusDto statusDto = null;
        Status status = StatusTransformer.toEntity(statusDto);
        assertNull(status);
    }

    @Test
    public void toDTO() {
        Status status = new Status();
        status.setId(10L);
        status.setTitle("LongTerm");
        StatusDto statusDto = StatusTransformer.toDTO(status);
        assertEquals(status.getId(), statusDto.getId());
        assertEquals(status.getTitle(), statusDto.getTitle());
    }
    @Test
    public void toDTO_null() {
        Status status = null;
        StatusDto statusDto = StatusTransformer.toDTO(status);
        assertNull(statusDto);
    }
}
