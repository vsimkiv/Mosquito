package com.softserve.mosquito.transformer;


import com.softserve.mosquito.dtos.*;
import com.softserve.mosquito.entities.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class EstimationTransformerTest {

    @Test
    public void toEntity() {
        EstimationDto estimationDto = new EstimationDto();
        estimationDto.setTimeEstimation(100);
        estimationDto.setRemaining(100);
        SpecializationDto specializationDto1 = new SpecializationDto();
        specializationDto1.setTitle("QC");
        SpecializationDto specializationDto2 = new SpecializationDto();
        specializationDto2.setTitle("QA");
        Set<SpecializationDto> specializationDtos = new HashSet<>();
        specializationDtos.add(specializationDto1);
        specializationDtos.add(specializationDto2);
        UserDto userDto = UserDto.builder().email("test_email").password("test_passpword").
                firstName("test_name").lastName("test_surname").
                specializations(specializationDtos).build();
        LogWorkDto logWorkDto = new LogWorkDto();
        logWorkDto.setEstimationId(estimationDto.getId());
        logWorkDto.setLogged(10);
        logWorkDto.setUserId(userDto.getId());
        logWorkDto.setDescription("Test Description");
        List<LogWorkDto> logWorkDtos = new ArrayList<>();
        logWorkDtos.add(logWorkDto);
        estimationDto.setLogWorks(logWorkDtos);
        PriorityDto priorityDto = new PriorityDto();
        priorityDto.setTitle("High");
        StatusDto statusDto = new StatusDto();
        statusDto.setTitle("Doing");
        TaskFullDto taskFullDto = new TaskFullDto();
        taskFullDto.setName("Test TaskDto");
        taskFullDto.setStatusDto(statusDto);
        taskFullDto.setPriorityDto(priorityDto);
        taskFullDto.setOwnerDto(userDto);
        taskFullDto.setEstimationDto(estimationDto);
        estimationDto.setTaskId(taskFullDto.getId());
        Estimation estimation = EstimationTransformer.toEntity(estimationDto);

        assertEquals(estimationDto.getId(), estimation.getId());
        assertEquals(estimationDto.getRemaining(), estimation.getRemaining());



    }

    @Test
    public void toDTO() {
        Estimation estimation = new Estimation();
        estimation.setTimeEstimation(200);
        estimation.setRemaining(100);
        User user = new User();
        user.setEmail("test_email");
        user.setPassword("test_password");
        user.setFirstName("test_name");
        user.setLastName("test_surname");
        user.setConfirmed(true);
        Priority priority = new Priority("middle");
        Status status = new Status("TODO");
        Task task = Task.builder().name("Test task").estimation(estimation)
                .owner(user).worker(user).priority(priority).status(status).build();
        LogWork logWork = new LogWork();
        logWork.setAuthor(user);
        logWork.setEstimation(estimation);
        logWork.setLogged(100);
        logWork.setDescription("Test");
        List<LogWork> logWorks = new ArrayList<>();
        logWorks.add(logWork);
        estimation.setLogWorks(logWorks);
        estimation.setTask(task);
        EstimationDto estimationDto = EstimationTransformer.toDTO(estimation);
        assertEquals(estimation.getTimeEstimation(), estimationDto.getTimeEstimation());
        assertEquals(estimation.getRemaining(), estimationDto.getRemaining());
    }
    @Test
    public void toEntity_null() {
        EstimationDto estimationDto = null;
        Estimation e  = EstimationTransformer.toEntity(estimationDto);
        assertNull(e);
    }
    @Test
    public void toDTO_null() {
        Estimation estimation = null;
        EstimationDto estimationDto = EstimationTransformer.toDTO(estimation);
        assertNull(estimationDto);

    }
}