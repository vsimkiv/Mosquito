package com.softserve.mosquito.transformer.impl;

import com.softserve.mosquito.dtos.SpecializationCreateDto;
import com.softserve.mosquito.dtos.SpecializationDto;
import com.softserve.mosquito.entities.Specialization;

import java.util.Set;

public class SpecializationTransformer {

    public Set<Specialization> toEntity(Set<SpecializationDto> specializations) {
        return null;
    }

    public Set<SpecializationDto> toDTO(Set<Specialization> specializations) {
        return null;
    }

    public static Specialization toEntity(SpecializationCreateDto specializationCreateDto) {
        return new Specialization(specializationCreateDto.getTitle());
    }

    public static SpecializationCreateDto toDTO(Specialization specialization) {
        return new SpecializationCreateDto(specialization.getTitle());
    }

    public static Specialization toEntity(SpecializationDto specializationDto) {
        return new Specialization(specializationDto.getId(),
                specializationDto.getTitle());
    }
}
