package com.softserve.mosquito.transformer.impl;

import com.softserve.mosquito.dtos.SpecializationDto;
import com.softserve.mosquito.entities.Specialization;

import java.util.HashSet;
import java.util.Set;

public class SpecializationTransformer {

    public static Set<Specialization> toEntityList(Set<SpecializationDto> specializationDtos) {
        Set<Specialization> specializations = new HashSet<>();
        for(SpecializationDto specializationDto: specializationDtos){
            specializations.add(toEntity(specializationDto));
        }
        return specializations;
    }

    public static Set<SpecializationDto> toDTOList(Set<Specialization> specializations) {
        Set<SpecializationDto> specializationDtos = new HashSet<>();
        for(Specialization specialization: specializations){
            specializationDtos.add(toDTO(specialization));
        }
        return specializationDtos;
    }

    public static Specialization toEntity(SpecializationDto specializationDto) {
        return new Specialization(specializationDto.getId(), specializationDto.getTitle());
    }

    public static SpecializationDto toDTO(Specialization specialization) {
        return new SpecializationDto(specialization.getId(), specialization.getTitle());
    }

}
