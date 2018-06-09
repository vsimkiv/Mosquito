package com.softserve.mosquito.transformer.impl;

import com.softserve.mosquito.transformer.api.Transformer;
import com.softserve.mosquito.dtos.SpecializationCreateDto;
import com.softserve.mosquito.dtos.SpecializationDto;
import com.softserve.mosquito.entities.Specialization;

import java.util.Set;

public class SpecializationTransformer {

    private SpecializationTransformer() {
        throw new IllegalStateException("Utility class");
    }

    public Set<Specialization> toEntity(Set<SpecializationDto> specializations) {
        return null;
    }

    public Set<SpecializationDto> toDTO(Set<Specialization> specializations) {
        return null;
    }

    public static class SpecializationCreate implements Transformer<Specialization, SpecializationCreateDto>{

        @Override
        public Specialization toEntity(SpecializationCreateDto specializationCreateDto) {
            return new Specialization(specializationCreateDto.getTitle());
        }

        @Override
        public SpecializationCreateDto toDTO(Specialization specialization) {
            return new SpecializationCreateDto(specialization.getTitle());
        }
    }

    public static class SpecializationGeneric implements Transformer<Specialization, SpecializationDto>{

        @Override
        public Specialization toEntity(SpecializationDto specializationDto) {
            return new Specialization(specializationDto.getId(),
                    specializationDto.getTitle());
        }

        @Override
        public SpecializationDto toDTO(Specialization specialization) {
            return new SpecializationDto(specialization.getId(),
                    specialization.getTitle());
        }
    }

}
