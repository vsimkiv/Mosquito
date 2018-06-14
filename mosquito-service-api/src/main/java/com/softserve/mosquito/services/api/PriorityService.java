package com.softserve.mosquito.services.api;

import com.softserve.mosquito.dtos.PriorityDto;

import java.util.List;

public interface PriorityService {

    List<PriorityDto> getAll();

    PriorityDto getById(Long id);

    PriorityDto save(PriorityDto priorityDto);

    PriorityDto update(PriorityDto priorityDto);

    void delete(Long id);
}
