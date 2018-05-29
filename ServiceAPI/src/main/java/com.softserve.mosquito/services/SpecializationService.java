package com.softserve.mosquito.services;

import com.softserve.mosquito.dtos.SpecializationCreateDto;
import com.softserve.mosquito.dtos.SpecializationDto;

import java.util.List;

public interface SpecializationService {

    List<SpecializationDto> getAllSpecializations();

    SpecializationDto getSpecializationById(Long id);

    SpecializationDto createSpecialization(SpecializationCreateDto specialization);

    SpecializationDto updateSpecialization(SpecializationDto specialization);

    void removeSpecialization(SpecializationDto specialization);
}
