package com.softserve.mosquito.services.impl;

import com.softserve.mosquito.dtos.LogWorkDto;
import com.softserve.mosquito.entities.LogWork;
import com.softserve.mosquito.repo.api.LogWorkRepo;
import com.softserve.mosquito.services.api.LogWorkService;
import com.softserve.mosquito.transformer.impl.LogWorkTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LogWorkServiceImpl implements LogWorkService {

    private LogWorkRepo logWorkRepo;

    @Autowired
    public LogWorkServiceImpl(LogWorkRepo logWorkRepo) {
        this.logWorkRepo = logWorkRepo;
    }

    @Transactional
    @Override
    public LogWorkDto createLogWork(LogWorkDto logWorkDto) {
        LogWork logWork = LogWorkTransformer.toEntity(logWorkDto);
        logWorkRepo.create(logWork);
        return LogWorkTransformer.toDTO(logWork);
    }

    @Transactional
    @Override
    public LogWorkDto getLogWorkById(Long logWorkId) {
        LogWork logWork = logWorkRepo.read(logWorkId);
        return LogWorkTransformer.toDTO(logWork);
    }

    @Transactional
    @Override
    public LogWorkDto updateLogWork(LogWorkDto logWorkDto) {
        LogWork logWork = LogWorkTransformer.toEntity(logWorkDto);
        logWorkRepo.update(logWork);
        return LogWorkTransformer.toDTO(logWork);
    }

    @Transactional
    @Override
    public void removeLogWorkDto(Long id) {
        logWorkRepo.delete(id);
    }

    @Transactional
    @Override
    public List<LogWorkDto> getAllLogWorkDto() {
        List<LogWork> logWorks = logWorkRepo.readAll();
        return LogWorkTransformer.toDTOList(logWorks);
    }

    @Transactional
    public List<LogWorkDto> getLogWorksDtoByEstimation(Long userId) {
        List<LogWork> logWorks = logWorkRepo.getLogWorksByUser(userId);
        return LogWorkTransformer.toDTOList(logWorks);
    }
}
