package com.softserve.mosquito.repository;

import com.softserve.mosquito.api.Transformer;
import com.softserve.mosquito.dtos.StatusCreateDto;
import com.softserve.mosquito.dtos.StatusDto;
import com.softserve.mosquito.entities.Status;

public class StatusTransformer {

    public static class StatusCreate implements Transformer<Status, StatusCreateDto>{

        @Override
        public Status toEntity(StatusCreateDto statusCreateDto) {
            return new Status(statusCreateDto.getTitle());
        }

        @Override
        public StatusCreateDto toDTO(Status status) {
            return new StatusCreateDto(status.getTitle());
        }
    }

    public static class StatusGeneric implements Transformer<Status, StatusDto>{

        @Override
        public Status toEntity(StatusDto statusDto) {
            return new Status(statusDto.getId(), statusDto.getTitle());
        }

        @Override
        public StatusDto toDTO(Status status) {
            return new StatusDto(status.getId(), status.getTitle());
        }
    }

}
