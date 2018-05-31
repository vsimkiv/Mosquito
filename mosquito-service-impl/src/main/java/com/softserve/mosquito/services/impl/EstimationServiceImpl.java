package com.softserve.mosquito.services.impl;

import com.softserve.mosquito.entities.Estimation;
import com.softserve.mosquito.repo.api.EstimationRepo;
import com.softserve.mosquito.repo.impl.EstimationRepoImpl;
import com.softserve.mosquito.services.api.EstimationService;

public class EstimationServiceImpl implements EstimationService {

    private EstimationRepo estimationRepo = new EstimationRepoImpl();

    @Override
    public Estimation createEstimation(Estimation estimation) {
        return estimationRepo.create(estimation);
    }

    @Override
    public Estimation getEstimationById(Long id) {
        return estimationRepo.read(id);
    }

    @Override
    public Estimation updateEstimation(Estimation estimation) {
        return estimationRepo.update(estimation);
    }

    @Override
    public void removeEstimation(Long id) {
        estimationRepo.delete(id);
    }
}
