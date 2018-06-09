package com.softserve.mosquito.services.impl;

import com.softserve.mosquito.dtos.EstimationDto;
import com.softserve.mosquito.entities.Estimation;
import com.softserve.mosquito.repo.api.EstimationRepo;
import com.softserve.mosquito.repo.impl.EstimationRepoImpl;
import com.softserve.mosquito.services.api.EstimationService;
import com.softserve.mosquito.transformer.impl.EstimationTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EstimationServiceImpl implements EstimationService {
    private EstimationRepo estimationRepo;
    EstimationTransformer estimationTransformer;

    @Autowired
    public EstimationServiceImpl(EstimationRepo estimationRepo) {
        this.estimationRepo = estimationRepo;
    }


    @Override
    @Transactional
    public EstimationDto createEstimation(EstimationDto estimationDto) {
        Estimation estimation = estimationRepo.create(estimationTransformer.toEntity(estimationDto));
        if (estimation == null)
            return null;

        return estimationTransformer.toDTO(estimation);
    }


    @Override
    @Transactional
    public EstimationDto getEstimationById(Long id) {
        Estimation estimation = estimationRepo.read(id);
        if (estimation == null)
            return null;
        return estimationTransformer.toDTO(estimation);
    }

    @Override
    @Transactional
    public EstimationDto updateEstimationDto(EstimationDto estimationDto) {
        Estimation estimation = estimationRepo.update(estimationTransformer.toEntity(estimationDto));
        if (estimation == null)
            return null;
        return estimationTransformer.toDTO(estimation);
    }

    @Override
    @Transactional
    public void removeEstimation(Long id) {
        estimationRepo.delete(id);
    }
}

