package com.softserve.mosquito.transformer;

import com.softserve.mosquito.dtos.EstimationDto;
import com.softserve.mosquito.dtos.LogWorkDto;
import com.softserve.mosquito.dtos.SpecializationDto;
import com.softserve.mosquito.dtos.UserDto;
import com.softserve.mosquito.entities.Estimation;
import com.softserve.mosquito.entities.LogWork;
import com.softserve.mosquito.entities.User;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class LogWorkTransformerTest {

    @Test
   public void toEntity() {

        LogWorkDto logWorkDto = new LogWorkDto();
        logWorkDto.setDescription("TestDescription");
        logWorkDto.setLogged(100);
        SpecializationDto specializationDto1 = new SpecializationDto();
        specializationDto1.setTitle("QC");
        Set<SpecializationDto> specializationDtos = new HashSet<>();
        specializationDtos.add(specializationDto1);
        UserDto userDto = UserDto.builder().email("test_email").password("test_passpword").
                firstName("test_name").lastName("test_surname").
                specializations(specializationDtos).build();
        logWorkDto.setUserId(userDto.getId());
        EstimationDto estimationDto = new EstimationDto();
        estimationDto.setTimeEstimation(100);
        estimationDto.setRemaining(100);
        logWorkDto.setEstimationId(estimationDto.getId());
        LogWork logWork = LogWorkTransformer.toEntity(logWorkDto);
        assertEquals(logWorkDto.getDescription(), logWork.getDescription());

    }

    @Test
    public void toDTO() {
        LogWork logWork = new LogWork();
        logWork.setDescription("Test Logwork");
        logWork.setLogged(100);
        Estimation estimation = new Estimation();
        estimation.setTimeEstimation(25);
        estimation.setRemaining(25);
        User user = new User();
        user.setEmail("test_email");
        user.setPassword("test_password");
        user.setFirstName("test_name");
        user.setLastName("test_surname");
        user.setConfirmed(true);
        logWork.setEstimation(estimation);
        logWork.setAuthor(user);
        LogWorkDto logWorkDto = LogWorkTransformer.toDTO(logWork);
        assertEquals(logWork.getDescription(), logWorkDto.getDescription());
        assertEquals(logWork.getAuthor().getId(), logWorkDto.getUserId());
    }

    @Test
    public void toEntityList() {
        LogWorkDto logWorkDto1 = new LogWorkDto();
        logWorkDto1.setDescription("TestDescription");
        logWorkDto1.setLogged(100);
        SpecializationDto specializationDto1 = new SpecializationDto();
        specializationDto1.setTitle("QC");
        Set<SpecializationDto> specializationDtos = new HashSet<>();
        specializationDtos.add(specializationDto1);
        UserDto userDto = UserDto.builder().email("test_email").password("test_passpword").
                firstName("test_name").lastName("test_surname").
                specializations(specializationDtos).build();
        logWorkDto1.setUserId(userDto.getId());
        EstimationDto estimationDto = new EstimationDto();
        estimationDto.setTimeEstimation(100);
        estimationDto.setRemaining(100);
        logWorkDto1.setEstimationId(estimationDto.getId());
        List<LogWorkDto> logWorkDtos = new ArrayList<>();
        logWorkDtos.add(logWorkDto1);
        List<LogWork> logWorks = LogWorkTransformer.toEntityList(logWorkDtos);
        LogWork logWork1 = logWorks.get(0);
        assertEquals(logWorkDto1.getDescription(), logWork1.getDescription());
        assertEquals(logWorkDto1.getEstimationId(), logWork1.getEstimation().getId());
    }

    @Test
    public void toDTOList() {
        LogWork logWork = new LogWork();
        logWork.setDescription("Test Logwork");
        logWork.setLogged(100);
        Estimation estimation = new Estimation();
        estimation.setTimeEstimation(25);
        estimation.setRemaining(25);
        User user = new User();
        user.setEmail("test_email");
        user.setPassword("test_password");
        user.setFirstName("test_name");
        user.setLastName("test_surname");
        user.setConfirmed(true);
        logWork.setEstimation(estimation);
        logWork.setAuthor(user);
        List<LogWork> logWorks = new ArrayList<>();
        logWorks.add(logWork);
        List<LogWorkDto> logWorkDtos = LogWorkTransformer.toDTOList(logWorks);
        LogWorkDto logWorkDto = logWorkDtos.get(0);
        assertEquals(logWork.getAuthor().getId(), logWorkDto.getUserId());
    }
     @Test
     public void toEntity_null() {
        LogWorkDto logWorkDto =null;
        LogWork logWork = LogWorkTransformer.toEntity(logWorkDto);
        assertNull(logWork);
     }
    @Test
    public void toDTO_null() {
        LogWork logWork = null;
        LogWorkDto logWorkDto = LogWorkTransformer.toDTO(logWork);
        assertNull(logWorkDto);
    }
}
