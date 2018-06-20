package com.softserve.mosquito.services.api;

import com.softserve.mosquito.dtos.LogWorkDto;

import java.util.List;

public interface LogWorkService {

    LogWorkDto save(LogWorkDto logWork);

    LogWorkDto getById(Long logWorkId,byte timeZone);

    LogWorkDto update(LogWorkDto logWork);

    void delete(Long id);

    List<LogWorkDto> getByEstimationId(Long estimationId);

    List<LogWorkDto> getByUserId(Long userId);
}
