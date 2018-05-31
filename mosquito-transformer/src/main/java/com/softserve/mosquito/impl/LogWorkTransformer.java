package com.softserve.mosquito.impl;

import com.softserve.mosquito.api.Transformer;
import com.softserve.mosquito.dtos.LogWorkCreateDto;
import com.softserve.mosquito.entities.LogWork;

public class LogWorkTransformer {

    private LogWorkTransformer() {
        throw new IllegalStateException("Utility class");
    }

    static class LogWorkCreate implements Transformer<LogWork,LogWorkCreateDto>{

        @Override
        public LogWork toEntity(LogWorkCreateDto logWorkCreateDto) {
            return new LogWork(logWorkCreateDto.getDescription(),
                    logWorkCreateDto.getLogged(),
                    logWorkCreateDto.getUserId(),
                    logWorkCreateDto.getEstimationId());
        }

        @Override
        public LogWorkCreateDto toDTO(LogWork logWork) {
            return new LogWorkCreateDto(logWork.getDescription(),
                    logWork.getUserId(),
                    logWork.getLogged(),
                    logWork.getEstimationId());
        }
    }
}
