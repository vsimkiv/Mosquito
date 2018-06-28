package com.softserve.mosquito.transformer;

import com.softserve.mosquito.dtos.SpecializationDto;
import com.softserve.mosquito.entities.Specialization;

import java.util.HashSet;
import java.util.Set;

public class SpecializationTransformer {

    private SpecializationTransformer() {
    }

    public static Set<Specialization> toEntityList(Set<SpecializationDto> specializationDtos) {
        Set<Specialization> specializations = new HashSet<>();

        if(specializationDtos != null && !specializationDtos.isEmpty()) {
            for (SpecializationDto specializationDto : specializationDtos) {
                specializations.add(toEntity(specializationDto));
            }
        }
        return specializations;
    }

    public static Set<SpecializationDto> toDTOList(Set<Specialization> specializations) {
        Set<SpecializationDto> specializationDtos = new HashSet<>();

        if(specializations != null && !specializations.isEmpty()) {
            for (Specialization specialization : specializations) {
                specializationDtos.add(toDTO(specialization));
            }
        }
        return specializationDtos;
    }

    public static Specialization toEntity(SpecializationDto specializationDto) {
        if (specializationDto == null) {
            return null;
        }else
        return new Specialization(specializationDto.getId(), specializationDto.getTitle());
    }

    public static SpecializationDto toDTO(Specialization specialization) {
        if (specialization == null) {
            return null;
        }else
        return new SpecializationDto(specialization.getId(), specialization.getTitle());
    }

}
