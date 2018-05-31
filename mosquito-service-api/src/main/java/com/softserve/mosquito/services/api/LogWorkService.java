package com.softserve.mosquito.services.api;

import com.softserve.mosquito.entities.LogWork;

import java.util.List;

public interface LogWorkService {

    LogWork createLogWork(LogWork user);

    LogWork getLogWorkById(Long logWorkId);

    LogWork updateLogWork(LogWork logWork);

    void removeLogWork(LogWork logWork);

    List<LogWork> getAllLogWork();

    List<LogWork> getLogWorksByEstimation(Long estimationId);
}
