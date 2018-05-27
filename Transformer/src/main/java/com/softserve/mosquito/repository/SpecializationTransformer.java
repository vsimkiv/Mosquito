package com.softserve.mosquito.repository;

import com.softserve.mosquito.api.Transformer;
import com.softserve.mosquito.dtos.SpecializationCreateDto;
import com.softserve.mosquito.entities.Specialization;

public class SpecializationTransformer {

    static class SpecializationCreate implements Transformer<Specialization, SpecializationCreateDto>{

        @Override
        public Specialization toEntity(SpecializationCreateDto specializationCreateDto) {
            return new Specialization(specializationCreateDto.getTitle());
        }

        @Override
        public SpecializationCreateDto toDTO(Specialization specialization) {
            return new SpecializationCreateDto(specialization.getTitle());
        }
    }

}
