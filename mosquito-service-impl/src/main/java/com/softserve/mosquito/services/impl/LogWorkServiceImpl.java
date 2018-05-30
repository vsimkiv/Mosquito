package com.softserve.mosquito.services.impl;

import com.softserve.mosquito.entities.LogWork;
import com.softserve.mosquito.repo.api.LogWorkRepo;
import com.softserve.mosquito.repo.impl.LogWorkRepoImpl;
import com.softserve.mosquito.services.api.LogWorkService;

import java.util.List;

public class LogWorkServiceImpl implements LogWorkService {

    private LogWorkRepo logWorkRepo = new LogWorkRepoImpl();

    @Override
    public LogWork createLogWork(LogWork logWork) {
        return logWorkRepo.create(logWork);
    }

    @Override
    public LogWork getLogWorkById(Long logWorkId) { return logWorkRepo.read(logWorkId);}

    @Override
    public LogWork updateLogWork(LogWork logWork) { return logWorkRepo.update(logWork);}

    @Override
    public void removeLogWork(LogWork logWork) { logWorkRepo.delete(logWork); }

    @Override
    public List<LogWork> getAllLogWork() { return logWorkRepo.readAll(); }

    @Override
    public LogWork getUserByEstimation(Long estimationId) {
        return null;
    }
}
