package com.softserve.mosquito.repo.api;

import com.softserve.mosquito.entities.LogWork;

import java.util.List;

public interface LogWorkRepo extends GenericCRUD<LogWork> {
    public List<LogWork> getLogWorksByUser(Long userId);
}
