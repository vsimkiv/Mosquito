package com.softserve.mosquito.repository;

import com.softserve.mosquito.api.Transformer;
import com.softserve.mosquito.dtos.StatusCreateDto;
import com.softserve.mosquito.entities.Status;

public class StatusTransformer {

    static class StatusCreate implements Transformer<Status, StatusCreateDto>{

        @Override
        public Status toEntity(StatusCreateDto statusCreateDto) {
            return null;
        }

        @Override
        public StatusCreateDto toDTO(Status status) {
            return null;
        }
    }

}
