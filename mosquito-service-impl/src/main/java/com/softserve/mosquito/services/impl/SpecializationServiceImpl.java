package com.softserve.mosquito.services.impl;

import com.softserve.mosquito.dtos.SpecializationDto;
import com.softserve.mosquito.entities.Specialization;
import com.softserve.mosquito.repo.api.SpecializationRepo;
import com.softserve.mosquito.services.api.SpecializationService;
import com.softserve.mosquito.transformer.impl.SpecializationTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Service
public class SpecializationServiceImpl implements SpecializationService {

    private SpecializationRepo specializationRepo;

    @Autowired
    public SpecializationServiceImpl(SpecializationRepo specializationRepo) {
        this.specializationRepo = specializationRepo;
    }

    @Transactional
    @Override
    public Set<SpecializationDto> getAll(){
        Set<Specialization> specializations = new HashSet<>(specializationRepo.getAll());

        if(specializations == null || specializations.isEmpty()){
            return null;
        }

       return SpecializationTransformer.toDTOList(specializations);
    }

    @Transactional
    @Override
    public SpecializationDto getById(Long id){
        Specialization specialization = specializationRepo.read(id);
        if(specialization == null){
            return null;
        }
        return SpecializationTransformer.toDTO(specialization);
    }

    @Transactional
    @Override
    public SpecializationDto save(SpecializationDto specializationDto){
        Specialization createdSpecialization = specializationRepo.create(SpecializationTransformer.toEntity(specializationDto));
        if(createdSpecialization == null){
            return null;
        }
        return SpecializationTransformer.toDTO(createdSpecialization);
    }

    @Transactional
    @Override
    public SpecializationDto update(SpecializationDto specializationDto){
        Specialization updatedSpecialization = specializationRepo.update(SpecializationTransformer.toEntity(specializationDto));
        if(updatedSpecialization == null){
            return null;
        }
        return SpecializationTransformer.toDTO(updatedSpecialization);
    }

    @Transactional
    @Override
    public void delete(Long id){
        specializationRepo.delete(id);
    }
}
