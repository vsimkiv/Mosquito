package com.softserve.mosquito.repo.api;

import com.softserve.mosquito.entities.Status;

import java.util.List;

public interface StatusRepo extends GenericCRUD<Status> {

    List<Status> getAll();
}
