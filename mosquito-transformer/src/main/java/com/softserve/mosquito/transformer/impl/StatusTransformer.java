package com.softserve.mosquito.transformer.impl;

import com.softserve.mosquito.transformer.api.Transformer;
import com.softserve.mosquito.dtos.StatusCreateDto;
import com.softserve.mosquito.dtos.StatusDto;
import com.softserve.mosquito.entities.Status;

public class StatusTransformer implements Transformer<Status, StatusDto> {

    public StatusTransformer() {
    }

        @Override
        public Status toEntity(StatusDto statusDto) {
        if (statusDto == null) {
            return  null;
        }else
            return new Status(statusDto.getId(), statusDto.getTitle());
        }

        @Override
        public StatusDto toDTO(Status status) {
        if(status == null) {
            return null;
        }else
            return new StatusDto(status.getId(),
                    status.getTitle());
        }
    }


