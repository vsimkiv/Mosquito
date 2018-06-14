package com.softserve.mosquito.services.impl;

import com.softserve.mosquito.dtos.LogWorkDto;
import com.softserve.mosquito.entities.LogWork;
import com.softserve.mosquito.repo.api.LogWorkRepo;
import com.softserve.mosquito.services.api.LogWorkService;
import com.softserve.mosquito.transformer.LogWorkTransformer;
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
    public LogWorkDto save(LogWorkDto logWorkDto) {
        LogWork logWork = LogWorkTransformer.toEntity(logWorkDto);
        logWorkRepo.create(logWork);
        return LogWorkTransformer.toDTO(logWork);
    }

    @Transactional
    @Override
    public LogWorkDto getById(Long logWorkId) {
        LogWork logWork = logWorkRepo.read(logWorkId);
        return LogWorkTransformer.toDTO(logWork);
    }

    @Transactional
    @Override
    public LogWorkDto update(LogWorkDto logWorkDto) {
        LogWork logWork = LogWorkTransformer.toEntity(logWorkDto);
        logWorkRepo.update(logWork);
        return LogWorkTransformer.toDTO(logWork);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        logWorkRepo.delete(id);
    }

    @Transactional
    @Override
    public List<LogWorkDto> getByEstimationId(Long estimationId) {
        List<LogWork> logWorks = logWorkRepo.getByEstimationId(estimationId);
        return LogWorkTransformer.toDTOList(logWorks);
    }

    @Transactional
    public List<LogWorkDto> getByUserId(Long userId) {
        List<LogWork> logWorks = logWorkRepo.getByUserId(userId);
        return LogWorkTransformer.toDTOList(logWorks);
    }

}
