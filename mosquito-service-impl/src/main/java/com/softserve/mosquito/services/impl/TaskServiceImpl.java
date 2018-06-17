package com.softserve.mosquito.services.impl;

import com.softserve.mosquito.dtos.TaskFullDto;
import com.softserve.mosquito.dtos.TaskSimpleDto;
import com.softserve.mosquito.dtos.UserDto;
import com.softserve.mosquito.entities.Task;
import com.softserve.mosquito.repo.api.TaskRepo;
import com.softserve.mosquito.services.api.TaskService;
import com.softserve.mosquito.services.mail.MailSender;
import com.softserve.mosquito.transformer.TaskTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.softserve.mosquito.transformer.TaskTransformer.toBigDTO;
import static com.softserve.mosquito.transformer.TaskTransformer.toSimpleDto;

@Service
public class TaskServiceImpl implements TaskService {
    private TaskRepo taskRepo;
    private MailSender mailSender;

    @Autowired
    public TaskServiceImpl(TaskRepo taskRepo, MailSender mailSender) {
        this.taskRepo = taskRepo;
        this.mailSender = mailSender;
    }

    @Transactional
    @Override
    public TaskFullDto save(TaskFullDto taskFullDto) {

        if (isPresent(taskFullDto)) {
            Task existedTask = taskRepo.getByName(taskFullDto.getName());
            return TaskTransformer.toBigDTO(existedTask);
        }

        //TODO messaging exception "Could not convert socket to TLS..."
        /*if (isMessageSent(taskFullDto.getWorkerDto(),
                "You was assigned for this task" + taskFullDto.getName(),
                "Mosquito Task Manager")) {*/
        if (isPresent(taskFullDto)) return TaskTransformer.toBigDTO(taskRepo.getByName(taskFullDto.getName()));
        Task task = taskRepo.create(TaskTransformer.toEntity(taskFullDto));
        return toBigDTO(task);
        /*}
        return null;*/
    }

    @Transactional
    @Override
    public TaskFullDto update(TaskFullDto taskFullDto) {
        Task task = taskRepo.update(TaskTransformer.toEntity(taskFullDto));
        if (task == null)
            return null;
        return toBigDTO(task);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        taskRepo.delete(id);
    }

    @Transactional
    @Override
    public TaskFullDto getById(Long id) {
        Task task = taskRepo.read(id);
        TaskFullDto taskFullDto = toBigDTO(task);

        taskFullDto.setParentTaskFullDto(getParentTaskDto(taskFullDto.getId()));
        taskFullDto.setChildTaskFullDtoList(getSubTasks(taskFullDto.getId()));
        return taskFullDto;
    }

    @Transactional
    @Override
    public TaskSimpleDto getSimpleTaskById(Long id) {
        Task task = taskRepo.read(id);
        return toSimpleDto(toBigDTO(task));
    }

    @Transactional
    @Override
    public List<TaskFullDto> getSubTasks(Long id) {
        List<Task> tasks = taskRepo.getSubTasks(id);
        List<TaskFullDto> taskFullDtos = new ArrayList<>();
        for (Task task : tasks) {
            taskFullDtos.add(toBigDTO(task));
        }
        return taskFullDtos;
    }

    @Transactional
    @Override
    public TaskFullDto getParentTaskDto(Long parentId) {
        Task task = taskRepo.read(parentId);
        return toBigDTO(task);
    }

    @Transactional
    @Override
    public List<TaskFullDto> filterByOwner(Long ownerId) {
        List<TaskFullDto> taskFullDtoList = new ArrayList<>();
        return taskFullDtoList;
    }

    @Transactional
    @Override
    public List<TaskFullDto> filterByWorker(Long workerId) {
        List<TaskFullDto> taskFullDtoList = new ArrayList<>();
        return taskFullDtoList;
    }

    @Transactional
    @Override
    public List<TaskFullDto> filterByPriority(Long priorityId) {
        List<TaskFullDto> taskFullDtoList = new ArrayList<>();
        return taskFullDtoList;
    }

    @Transactional
    @Override
    public List<TaskFullDto> filterByStatus(Long statusId) {
        List<TaskFullDto> taskFullDtoList = new ArrayList<>();
        return taskFullDtoList;
    }

    @Override
    @Transactional
    public boolean isPresent(TaskFullDto taskFullDto) {
        return taskRepo.getByName(taskFullDto.getName()) != null;
    }

    @Override
    @Transactional
    public boolean isPresent(String name) {
        return (taskRepo.getByName(name) != null);
    }

    @Override
    @Transactional
    public TaskFullDto getByName(String name) {
        return TaskTransformer.toBigDTO(taskRepo.getByName(name));
    }


    private boolean isMessageSent(UserDto userDto, String message, String subject) {
        return mailSender.sendMessage(userDto, message, subject);
    }
}
