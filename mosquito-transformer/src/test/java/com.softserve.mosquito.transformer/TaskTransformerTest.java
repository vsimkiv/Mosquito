package com.softserve.mosquito.transformer;

import com.softserve.mosquito.dtos.*;
import com.softserve.mosquito.entities.*;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

public class TaskTransformerTest {

    @Test
    public void toEntity() {
        EstimationDto estimationDto = new EstimationDto();
        estimationDto.setTimeEstimation(100);
        estimationDto.setRemaining(100);
        PriorityDto priorityDto = new PriorityDto();
        priorityDto.setTitle("High");
        StatusDto statusDto = new StatusDto();
        statusDto.setTitle("Doing");
        SpecializationDto specializationDto1 = new SpecializationDto();
        specializationDto1.setTitle("QC");
        Set<SpecializationDto> specializationDtos = new HashSet<>();
        specializationDtos.add(specializationDto1);
        UserDto userDto = UserDto.newBuilder().email("test_email").password("test_passpword").
                firstName("test_name").lastName("test_surname").
                specializations(specializationDtos).build();
        TaskFullDto taskFullDto = TaskFullDto.builder().ownerDto(userDto).parentTaskDto(null).
                workerDto(userDto).estimationDto(estimationDto).priorityDto(priorityDto).statusDto(statusDto).build();
        Task task = TaskTransformer.toEntity(taskFullDto);
        assertEquals(taskFullDto.getName(), task.getName());
        assertEquals(taskFullDto.getOwnerDto().getId(), task.getOwner().getId());
        assertEquals(taskFullDto.getId(), task.getId());
    }

    @Test
    @Ignore
    public void toFullDTO() {
        Comment comment = new Comment();
        comment.setText("Test Comment Text");
        comment.setId(20L);
        List<Comment> comments = new ArrayList<>();
        comments.add(comment);
        Estimation estimation = new Estimation();
        estimation.setTimeEstimation(25);
        estimation.setRemaining(25);
        User user = new User();
        user.setEmail("test_email");
        user.setPassword("test_password");
        user.setFirstName("test_name");
        user.setLastName("test_surname");
        user.setConfirmed(true);
        Priority priority = new Priority("middle");
        Status status = new Status("TODO");
        Task task = Task.builder().parentTask(null).childTasks(null).name("Test Task").
                comments(comments).estimation(estimation).owner(user).priority(priority).
                status(status).worker(user).build();
        TaskFullDto taskFullDto = TaskTransformer.toFullDTO(task);
        assertEquals(task.getId(), taskFullDto.getId());

    }

    @Test
    @Ignore
    public void toSimpleDto() {
        Comment comment = new Comment();
        comment.setText("Test Comment Text");
        comment.setId(20L);
        List<Comment> comments = new ArrayList<>();
        comments.add(comment);
        Estimation estimation = new Estimation();
        estimation.setTimeEstimation(25);
        estimation.setRemaining(25);
        User user = new User();
        user.setEmail("test_email");
        user.setPassword("test_password");
        user.setFirstName("test_name");
        user.setLastName("test_surname");
        user.setConfirmed(true);
        Priority priority = new Priority("middle");
        Status status = new Status("TODO");
        Task task = new Task();
        task.setChildTasks(null);
        task.setComments(comments);
        task.setEstimation(estimation);
        task.setName("Test Task");
        task.setOwner(user);
        task.setPriority(priority);
        task.setStatus(status);
        task.setWorker(user);
        TaskSimpleDto taskSimpleDto = TaskTransformer.toSimpleDto(task);
        //assertEquals(task.getId(), taskSimpleDto.getId());
        assertEquals(task.getStatus(), taskSimpleDto.getStatus());

    }

    @Test
    public void toEntityList() {
        EstimationDto estimationDto = new EstimationDto();
        estimationDto.setTimeEstimation(100);
        estimationDto.setRemaining(100);
        PriorityDto priorityDto = new PriorityDto();
        priorityDto.setTitle("High");
        StatusDto statusDto = new StatusDto();
        statusDto.setTitle("Doing");
        SpecializationDto specializationDto1 = new SpecializationDto();
        specializationDto1.setTitle("QC");
        Set<SpecializationDto> specializationDtos = new HashSet<>();
        specializationDtos.add(specializationDto1);
        UserDto userDto = UserDto.newBuilder().email("test_email").password("test_passpword").
                firstName("test_name").lastName("test_surname").
                specializations(specializationDtos).build();
        TaskFullDto taskFullDto = TaskFullDto.builder().ownerDto(userDto).parentTaskDto(null).
                workerDto(userDto).estimationDto(estimationDto).priorityDto(priorityDto).statusDto(statusDto).build();
        List<TaskFullDto> taskFullDtos = new ArrayList<>();
        taskFullDtos.add(taskFullDto);
        List<Task> tasks = TaskTransformer.toEntityList(taskFullDtos);
        Task task1 = tasks.get(0);
        assertEquals(taskFullDto.getId(), task1.getId());
        assertEquals(taskFullDto.getName(), task1.getName());
    }

    @Test
    @Ignore
    public void toDTOList() {
        Comment comment = new Comment();
        comment.setText("Test Comment Text");
        comment.setId(20L);
        List<Comment> comments = new ArrayList<>();
        comments.add(comment);
        Estimation estimation = new Estimation();
        estimation.setTimeEstimation(25);
        estimation.setRemaining(25);
        User user = new User();
        user.setEmail("test_email");
        user.setPassword("test_password");
        user.setFirstName("test_name");
        user.setLastName("test_surname");
        user.setConfirmed(true);
        Priority priority = new Priority("middle");
        Status status = new Status("TODO");
        Task task = Task.builder().parentTask(null).childTasks(null).name("Test Task").
                comments(comments).estimation(estimation).owner(user).priority(priority).
                status(status).worker(user).build();
        List<Task> tasks = new ArrayList<>();
        tasks.add(task);
        List<TaskFullDto> taskFullDtos = TaskTransformer.toDTOList(tasks);
        TaskFullDto taskFullDto = taskFullDtos.get(0);
        assertEquals(task.getId(), taskFullDto.getId());
        assertEquals(task.getName(),taskFullDto.getName());
    }
    @Test
    public void toEntity_null() {
        TaskFullDto taskFullDto=null;
        Task task = TaskTransformer.toEntity(taskFullDto);
        assertNull(task);
    }
    @Test
    public void toFullDTO_null() {
        Task task= null;
        TaskFullDto taskFullDto = TaskTransformer.toFullDTO(task);
        assertNull(taskFullDto);
    }
    @Test
    public void toSimpleDto_null() {
        Task task = null;
        TaskSimpleDto taskSimpleDto = TaskTransformer.toSimpleDto(task);
        assertNull(taskSimpleDto);
    }
}