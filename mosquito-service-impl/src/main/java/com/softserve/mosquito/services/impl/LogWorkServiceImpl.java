package com.softserve.mosquito.services.impl;

import com.softserve.mosquito.dtos.LogWorkDto;
import com.softserve.mosquito.entities.LogWork;
import com.softserve.mosquito.repo.api.LogWorkRepo;
import com.softserve.mosquito.repo.impl.LogWorkRepoImpl;
import com.softserve.mosquito.services.api.LogWorkService;
import com.softserve.mosquito.transformer.impl.LogWorkTransformer;
import org.springframework.stereotype.Service;


import java.util.List;
@Service
public class LogWorkServiceImpl implements LogWorkService {

    private LogWorkRepo logWorkRepo = new LogWorkRepoImpl();

    @Override
    public LogWorkDto createLogWork(LogWorkDto logWorkDto) {
        LogWork logWork = LogWorkTransformer.toEntity(logWorkDto);
        logWorkRepo.create(logWork);
        return LogWorkTransformer.toDTO(logWork);}

    @Override
    public LogWorkDto getLogWorkById(Long logWorkId) {
        LogWork logWork = logWorkRepo.read(logWorkId);
         return LogWorkTransformer.toDTO(logWork);}

    @Override
    public LogWorkDto updateLogWork(LogWorkDto logWorkDto) {
        LogWork logWork = LogWorkTransformer.toEntity(logWorkDto);
         logWorkRepo.update(logWork);
         return LogWorkTransformer.toDTO(logWork);}

    @Override
    public void removeLogWorkDto(Long id) { logWorkRepo.delete(id); }

    @Override
    public List<LogWorkDto> getAllLogWorkDto(){
        List<LogWork> logWorks = logWorkRepo.readAll();
        List<LogWorkDto> logWorksDtos;
        return logWorksDtos = LogWorkTransformer.toDTOList(logWorks);}


    public List<LogWorkDto> getLogWorksDtoByEstimation(Long userId) {
        List<LogWork> logWorks = logWorkRepo.getLogWorksByUser(userId);
        List<LogWorkDto> logWorksDtos=null;
        return logWorksDtos = LogWorkTransformer.toDTOList(logWorks);}



}
