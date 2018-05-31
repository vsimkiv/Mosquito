package com.softserve.mosquito.services.impl;

import com.softserve.mosquito.api.Transformer;
import com.softserve.mosquito.dtos.SpecializationCreateDto;
import com.softserve.mosquito.dtos.SpecializationDto;
import com.softserve.mosquito.entities.Specialization;
import com.softserve.mosquito.repo.api.SpecializationRepo;
import com.softserve.mosquito.repo.impl.SpecializationRepoImpl;
import com.softserve.mosquito.impl.SpecializationTransformer;
import com.softserve.mosquito.services.api.SpecializationService;

import java.util.ArrayList;
import java.util.List;

public class SpecializationServiceImpl implements SpecializationService {

    private SpecializationRepo specializationRepo = new SpecializationRepoImpl();
    private Transformer<Specialization, SpecializationCreateDto> specializationCreateTransformer = new SpecializationTransformer.SpecializationCreate();
    private Transformer<Specialization, SpecializationDto> specializationGenericTransformer = new SpecializationTransformer.SpecializationGeneric();

    @Override
    public List<SpecializationDto> getAllSpecializations(){
        List<Specialization> specializations = specializationRepo.readAll();

        if(specializations == null || specializations.isEmpty()){
            return null;
        }

        List<SpecializationDto> specializationDtos = new ArrayList<>();
        for (Specialization specialization: specializations) {
            specializationDtos.add(specializationGenericTransformer.toDTO(specialization));
        }
        return specializationDtos;
    }

    @Override
    public SpecializationDto getSpecializationById(Long id){
        Specialization specialization = specializationRepo.read(id);
        if(specialization == null){
            return null;
        }

        return specializationGenericTransformer.toDTO(specialization);
    }

    @Override
    public SpecializationDto createSpecialization(SpecializationCreateDto specialization){
        Specialization createdSpecialization = specializationRepo.create(specializationCreateTransformer.toEntity(specialization));
        if(createdSpecialization == null){
            return null;
        }
        return specializationGenericTransformer.toDTO(createdSpecialization);
    }

    @Override
    public SpecializationDto updateSpecialization(SpecializationDto specialization){
        Specialization updatedSpecialization = specializationRepo.update(specializationGenericTransformer.toEntity(specialization));
        if(updatedSpecialization == null){
            return null;
        }
        return specializationGenericTransformer.toDTO(updatedSpecialization);
    }

    @Override
    public void removeSpecialization(Long id){
        specializationRepo.delete(id);
    }
}
