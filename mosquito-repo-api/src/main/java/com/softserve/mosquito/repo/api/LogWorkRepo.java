package com.softserve.mosquito.repo.api;

import com.softserve.mosquito.entities.LogWork;

import java.util.List;

public interface LogWorkRepo extends GenericCRUD<LogWork> {


    List<LogWork> getByUserId(Long userId);

    List<LogWork> getByEstimationId(Long estimationId);

    List<LogWork> readAll();
}
