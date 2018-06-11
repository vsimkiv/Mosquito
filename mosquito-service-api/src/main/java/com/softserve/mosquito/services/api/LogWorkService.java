package com.softserve.mosquito.services.api;

import com.softserve.mosquito.dtos.LogWorkDto;
import com.softserve.mosquito.entities.LogWork;

import java.util.List;

public interface LogWorkService {

    LogWorkDto createLogWork(LogWorkDto logWork);

    LogWorkDto getLogWorkById(Long logWorkId);

    LogWorkDto updateLogWork(LogWorkDto logWork);

    void removeLogWorkDto(Long id);

    List<LogWorkDto> getAllLogWorkDto();

    List<LogWorkDto> getLogWorksDtoByEstimation(Long estimationId);

    List<LogWorkDto> getLogWorksByUser(Long userId);
}
