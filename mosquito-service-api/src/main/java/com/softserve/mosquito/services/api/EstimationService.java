package com.softserve.mosquito.services.api;

import com.softserve.mosquito.dtos.EstimationDto;

public interface EstimationService {

    EstimationDto createEstimation(EstimationDto estimationDto);

    EstimationDto getEstimationById(Long id);

    EstimationDto updateEstimationDto(EstimationDto estimationDto);

    void removeEstimation(Long id);

    }
