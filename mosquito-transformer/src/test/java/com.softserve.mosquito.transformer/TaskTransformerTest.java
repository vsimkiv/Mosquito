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

public class TaskTransformerTest {

    @Test
    public void toEntity() {
        EstimationDto estimationDto = new EstimationDto();
        estimationDto.setTimeEstimation(100);
        estimationDto.setId(1L);
        estimationDto.setRemaining(100);
        PriorityDto priorityDto = new PriorityDto();
        priorityDto.setTitle("High");
        priorityDto.setId(2L);
        StatusDto statusDto = new StatusDto();
        statusDto.setId(3L);
        statusDto.setTitle("Doing");
        SpecializationDto specializationDto1 = new SpecializationDto();
        specializationDto1.setId(4L);
        specializationDto1.setTitle("QC");
        Set<SpecializationDto> specializationDtos = new HashSet<>();
        specializationDtos.add(specializationDto1);
        UserDto userDto = UserDto.builder().email("test_email").password("test_passpword").id(1L).
                firstName("test_name").lastName("test_surname").
                specializations(specializationDtos).build();
        TaskCreateDto taskCreateDto = TaskCreateDto.builder().id(1L).name("TestTask")
                .estimationTime(estimationDto.getTimeEstimation()).ownerId(userDto.getId())
                .parentId(null).priorityId(priorityDto.getId()).statusId(statusDto.getId())
                .workerId(userDto.getId()).trelloId("12336556dgdf").build();

        Task task = TaskTransformer.toEntity(taskCreateDto);
        assertEquals(taskCreateDto.getName(), task.getName());
        assertEquals(taskCreateDto.getOwnerId(), task.getOwner().getId());
        assertEquals(taskCreateDto.getId(), task.getId());
    }

    @Test
    public void toTaskDTO() {
        Comment comment = new Comment();
        comment.setId(1L);
        comment.setText("Test Comment Text");
        comment.setId(20L);
        List<Comment> comments = new ArrayList<>();
        comments.add(comment);
        Estimation estimation = new Estimation();
        estimation.setId(2L);
        estimation.setTimeEstimation(25);
        estimation.setRemaining(25);
        User user = new User();
        user.setId(2L);
        user.setEmail("test_email");
        user.setPassword("test_password");
        user.setFirstName("test_name");
        user.setLastName("test_surname");
        user.setConfirmed(true);
        Priority priority = new Priority();
        priority.setTitle("middle");
        priority.setId(5L);
        Status status = new Status();
        status.setTitle("TODO");
        status.setId(6L);
        Task task = Task.builder().parentTask(null).childTasks(null).name("Test Task").id(2L).
                comments(comments).estimation(estimation).owner(user).priority(priority).
                status(status).worker(user).build();
        TaskDto taskDto = TaskTransformer.toTaskDto(task);
        assertEquals(task.getId(), taskDto.getId());

    }



    @Test
    public void toTaskDtoList() {
        Comment comment = new Comment();
        comment.setId(4L);
        comment.setText("Test Comment Text");
        comment.setId(20L);
        List<Comment> comments = new ArrayList<>();
        comments.add(comment);
        Estimation estimation = new Estimation();
        estimation.setId(2L);
        estimation.setTimeEstimation(25);
        estimation.setRemaining(25);
        User user = new User();
        user.setId(2L);
        user.setEmail("test_email");
        user.setPassword("test_password");
        user.setFirstName("test_name");
        user.setLastName("test_surname");
        user.setConfirmed(true);
        Priority priority = new Priority();
        priority.setTitle("middle");
        priority.setId(2L);
        Status status = new Status();
        status.setTitle("TODO");
        status.setId(3L);
        Task task = Task.builder().parentTask(null).childTasks(null).name("Test Task").id(5L).
                comments(comments).estimation(estimation).owner(user).priority(priority).
                status(status).worker(user).build();
        List<Task> tasks = new ArrayList<>();
        tasks.add(task);
        List<TaskDto> taskDtos = TaskTransformer.toTaskDtoList(tasks);
        TaskDto taskDto = taskDtos.get(0);
        assertEquals(task.getId(), taskDto.getId());
        assertEquals(task.getName(),taskDto.getName());
    }
    @Test
    public void toEntity_null() {
        TaskCreateDto taskCreateDto=null;
        Task task = TaskTransformer.toEntity(taskCreateDto);
        assertNull(task);
    }
    @Test
    public void toTaskDTO_null() {
        Task task= null;
        TaskDto taskDto = TaskTransformer.toTaskDto(task);
        assertNull(taskDto);
    }

}