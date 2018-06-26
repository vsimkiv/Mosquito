package com.softserve.mosquito.transformer;

import com.softserve.mosquito.dtos.StatusDto;
import com.softserve.mosquito.entities.Status;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;


public class StatusTransformerTest {

    @Test
    public void toEntity() {
        StatusDto statusDto = new StatusDto();
        statusDto.setId(20L);
        statusDto.setTitle("Cancelled");
        Status status = new Status();
        status.setId(statusDto.getId());
        status.setTitle(statusDto.getTitle());
        assertEquals(statusDto.getId(), status.getId());
        assertEquals(statusDto.getTitle(), status.getTitle());

    }

    @Test
    public void toDTO() {
        Status status = new Status();
        status.setId(10L);
        status.setTitle("LongTerm");
        StatusDto statusDto = new StatusDto();
        statusDto.setId(status.getId());
        statusDto.setTitle(status.getTitle());
        assertEquals(status.getId(), statusDto.getId());
        assertEquals(status.getTitle(), statusDto.getTitle());
    }
}
