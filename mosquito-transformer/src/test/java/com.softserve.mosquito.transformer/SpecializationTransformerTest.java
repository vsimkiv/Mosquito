package com.softserve.mosquito.transformer;

import com.softserve.mosquito.dtos.SpecializationDto;
import com.softserve.mosquito.entities.Specialization;
import org.junit.Test;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class SpecializationTransformerTest {


    @Test
    public void toEntityList() {
        SpecializationDto specializationDto = new SpecializationDto();
        specializationDto.setId(20L);
        specializationDto.setTitle("JuniorJavaDeveloper");
        SpecializationDto specializationDto1 = new SpecializationDto();
        specializationDto1.setId(35L);
        specializationDto1.setTitle("MiddleJavaDeveloper");
        Set<SpecializationDto> specializationDtoSet = new HashSet<>();
        specializationDtoSet.add(specializationDto);
        specializationDtoSet.add(specializationDto1);
        Set<Specialization> specializations = new HashSet<>();
        Iterator<SpecializationDto> iter = specializationDtoSet.iterator();
        for (SpecializationDto specializationDtoo : specializationDtoSet) {
            specializations.add(SpecializationTransformer.toEntity(specializationDtoo));//Чи можна викликати toEntity
        }

    }

    @Test
    public void toDTOList() {
        Specialization specialization1 = new Specialization();
        specialization1.setId(40L);
        specialization1.setTitle("BigBoss");
        Specialization specialization2 = new Specialization();
        specialization2.setId(50L);
        specialization2.setTitle("Clerk");
        Set<Specialization> specializations = new HashSet<>();
        specializations.add(specialization1);
        specializations.add(specialization2);
        Set<SpecializationDto> specializationDtos = new HashSet<>();
        for (Specialization specializationn: specializations) {
            specializationDtos.add(SpecializationTransformer.toDTO(specializationn));
        }
    }

    @Test
    public void toEntity() {
        SpecializationDto specializationDto = new SpecializationDto();
        specializationDto.setId(10L);
        specializationDto.setTitle("CEO");

        Specialization specialization = new Specialization();
        specialization.setId(specializationDto.getId());
        specialization.setTitle(specializationDto.getTitle());
        assertEquals(specializationDto.getId(), specialization.getId());
        assertEquals(specializationDto.getTitle(), specialization.getTitle());
    }

    @Test
    public void toDTO() {
        Specialization specialization = new Specialization();
        specialization.setId(15L);
        specialization.setTitle("Director");
        SpecializationDto specializationDto = new SpecializationDto();
        specializationDto.setId(specialization.getId());
        specializationDto.setTitle(specialization.getTitle());
        assertEquals(specialization.getId(), specializationDto.getId());
        assertEquals(specialization.getTitle(), specializationDto.getTitle());
    }
}
