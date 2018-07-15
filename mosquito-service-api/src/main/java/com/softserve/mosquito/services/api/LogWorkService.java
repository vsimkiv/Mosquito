package com.softserve.mosquito.services.api;

import com.softserve.mosquito.dtos.LogWorkDto;

import java.util.List;

public interface LogWorkService {

    LogWorkDto save(Long estId, LogWorkDto logWork, int remaining);

    LogWorkDto getById(Long logWorkId);

    LogWorkDto update(LogWorkDto logWork, int remaining);

    void delete(Long id);

    List<LogWorkDto> getByEstimationId(Long estimationId);

    List<LogWorkDto> getByUserId(Long userId);
}
