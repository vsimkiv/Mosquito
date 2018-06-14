package com.softserve.mosquito.transformer;

import com.softserve.mosquito.dtos.StatusDto;
import com.softserve.mosquito.entities.Status;

public class StatusTransformer {

    private StatusTransformer() {
    }

    public static Status toEntity(StatusDto statusDto) {
        if (statusDto == null) {
            return null;
        } else
            return new Status(statusDto.getId(), statusDto.getTitle());
    }


    public static StatusDto toDTO(Status status) {
        if (status == null) {
            return null;
        } else
            return new StatusDto(status.getId(),
                    status.getTitle());
    }
}


