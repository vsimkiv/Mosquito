package com.softserve.mosquito.services.api;

import com.softserve.mosquito.dtos.SpecializationDto;

import java.util.Set;

public interface SpecializationService {

    Set<SpecializationDto> getAll();

    SpecializationDto getById(Long id);

    SpecializationDto save(SpecializationDto specialization);

    SpecializationDto update(SpecializationDto specialization);

    void delete(Long id);
}
