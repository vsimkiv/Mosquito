package com.softserve.mosquito.services.api;

import com.softserve.mosquito.dtos.EstimationDto;
import com.softserve.mosquito.entities.Estimation;

public interface EstimationService {

    EstimationDto createEstimation(EstimationDto estimationDto);

    EstimationDto getEstimationById(Long id);

    EstimationDto updateEstimationDto(EstimationDto estimationDto);

    void removeEstimation(Long id);

    }
