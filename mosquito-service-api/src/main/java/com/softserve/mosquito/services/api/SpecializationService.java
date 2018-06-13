package com.softserve.mosquito.services.api;

import com.softserve.mosquito.dtos.SpecializationCreateDto;
import com.softserve.mosquito.dtos.SpecializationDto;

import java.util.List;

public interface SpecializationService {

    List<SpecializationDto> getAll();

    SpecializationDto getById(Long id);

    SpecializationDto save(SpecializationCreateDto specialization);

    SpecializationDto update(SpecializationDto specialization);

    void delete(Long id);
}
