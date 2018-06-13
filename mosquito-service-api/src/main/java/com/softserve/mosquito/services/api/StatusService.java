package com.softserve.mosquito.services.api;

import com.softserve.mosquito.dtos.StatusDto;
import com.softserve.mosquito.entities.Status;

import java.util.List;

public interface StatusService {

    List<StatusDto> getAll();

    StatusDto getById(Long id);

    StatusDto save(StatusDto statusDto);

    StatusDto update(StatusDto statusDto);

    void delete(Long id);

    StatusDto getByName(String title);

    /**
     * method for using in class TaskTransformer
     */
    Status getStatusEntityById(Long id);
}
