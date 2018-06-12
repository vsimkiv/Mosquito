package com.softserve.mosquito.repo.api;

import com.softserve.mosquito.entities.Priority;

import java.util.List;

public interface PriorityRepo extends GenericCRUD<Priority> {
    List<Priority> readAll();
}
