package com.softserve.mosquito.services.impl;

import com.softserve.mosquito.dtos.SpecializationCreateDto;
import com.softserve.mosquito.dtos.SpecializationDto;
import com.softserve.mosquito.entities.Specialization;
import com.softserve.mosquito.repo.api.SpecializationRepo;
import com.softserve.mosquito.repo.impl.SpecializationRepoImpl;
import com.softserve.mosquito.impl.SpecializationTransformer;
import com.softserve.mosquito.services.api.SpecializationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class SpecializationServiceImpl implements SpecializationService {

    private SpecializationRepo specializationRepo;
    //TODO: Autowired
    private ModelMapper modelMapper = new ModelMapper();

    @Autowired
    public SpecializationServiceImpl(SpecializationRepo specializationRepo) {
        this.specializationRepo = specializationRepo;
    }

    @Transactional
    @Override
    public List<SpecializationDto> getAllSpecializations(){
        List<Specialization> specializations = specializationRepo.readAll();

        if(specializations == null || specializations.isEmpty()){
            return null;
        }

        List<SpecializationDto> specializationDtos = new ArrayList<>();
        for (Specialization specialization: specializations) {
            specializationDtos.add(modelMapper.map(specialization, SpecializationDto.class));
        }
        return specializationDtos;
    }

    @Transactional
    @Override
    public SpecializationDto getSpecializationById(Long id){
        Specialization specialization = specializationRepo.read(id);
        if(specialization == null){
            return null;
        }
        return modelMapper.map(specialization, SpecializationDto.class);
    }

    @Transactional
    @Override
    public SpecializationDto createSpecialization(SpecializationCreateDto specializationCreateDto){
        Specialization createdSpecialization = specializationRepo.create(modelMapper.map(specializationCreateDto,
                                                                            Specialization.class));
        if(createdSpecialization == null){
            return null;
        }
        System.out.println(createdSpecialization + " " + createdSpecialization.getTitle());
        return modelMapper.map(createdSpecialization, SpecializationDto.class);
    }

    @Transactional
    @Override
    public SpecializationDto updateSpecialization(SpecializationDto specializationDto){
        Specialization updatedSpecialization = specializationRepo.update(modelMapper.map(specializationDto,
                                                                            Specialization.class));
        if(updatedSpecialization == null){
            return null;
        }
        return modelMapper.map(updatedSpecialization, SpecializationDto.class);
    }

    @Transactional
    @Override
    public void removeSpecialization(Long id){
        specializationRepo.delete(id);
    }
}
