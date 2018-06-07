package com.softserve.mosquito.transformer.impl;

import com.softserve.mosquito.transformer.api.Transformer;
import com.softserve.mosquito.dtos.LogWorkDto;
import com.softserve.mosquito.entities.LogWork;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class LogWorkTransformer {

    private LogWorkTransformer() {
        throw new IllegalStateException("Utility class");
    }


    static class LogWorkCreate implements Transformer<LogWork,LogWorkDto>{

        @Override
        public LogWork toEntity(LogWorkDto logWorkDto) {
            return new LogWork(logWorkDto.getDescription(),
                    logWorkDto.getLogged(),
                    logWorkDto.getUserId(),
                    logWorkDto.getEstimationId());
        }

        @Override
        public LogWorkDto toDTO(LogWork logWork) {
            /*return new LogWorkDto(logWork.getDescription(),
                    logWork.getUserId(),
                    logWork.getLogged(),
                    logWork.getEstimationId());*/
            throw new NotImplementedException();
        }
    }
}
