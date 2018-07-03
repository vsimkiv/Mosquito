package com.softserve.mosquito.services.impl;

import com.softserve.mosquito.dtos.LogWorkDto;
import com.softserve.mosquito.entities.Estimation;
import com.softserve.mosquito.entities.LogWork;
import com.softserve.mosquito.repo.api.EstimationRepo;
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
    private EstimationRepo estimationRepo;

    @Autowired
    public LogWorkServiceImpl(LogWorkRepo logWorkRepo) {
        this.logWorkRepo = logWorkRepo;
    }


    @Transactional
    @Override
    public LogWorkDto save(LogWorkDto logWorkDto, int remaining) {
        LogWork logWork = LogWorkTransformer.toEntity(logWorkDto);
        Estimation estimation = estimationRepo.read(logWork.getEstimation().getId());
        estimation.setRemaining(remaining);
        estimationRepo.update(estimation);
        logWorkRepo.create(logWork);
        return LogWorkTransformer.toDTO(logWork);
    }

    @Transactional
    @Override
    public LogWorkDto getById(Long logWorkId, byte timeZone) {
        LogWork logWork = logWorkRepo.read(logWorkId);
        logWork.setLastUpdate(logWork.getLastUpdate().plusHours(timeZone));
        return LogWorkTransformer.toDTO(logWork);
    }

    @Transactional
    @Override
    public LogWorkDto update(LogWorkDto logWorkDto, int remaining) {
        LogWork logWork = logWorkRepo.update( LogWorkTransformer.toEntity(logWorkDto));
        Estimation estimation = estimationRepo.read(logWork.getEstimation().getId());
        estimation.setRemaining(remaining);
        estimationRepo.update(estimation);

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
