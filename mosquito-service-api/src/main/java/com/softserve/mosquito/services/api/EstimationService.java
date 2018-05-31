package com.softserve.mosquito.services.api;

import com.softserve.mosquito.entities.Estimation;

public interface EstimationService {

    Estimation createEstimation(Estimation estimation);

    Estimation getEstimationById(Long id);

    Estimation updateEstimation(Estimation estimation);

    void removeEstimation(Long id);

    }
