package com.softserve.mosquito.services.impl;

import com.softserve.mosquito.dtos.TaskDto;
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

import static com.softserve.mosquito.transformer.TaskTransformer.toDTO;

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
    public TaskDto save(TaskDto taskDto) {

        if (isPresent(taskDto)) return TaskTransformer.toDTO(taskRepo.getByName(taskDto.getName()));

        if (isMessageSent(taskDto.getWorkerDto(), "You was assigned for this task" + taskDto.getName(), "Mosquito Task Manager")) {
            if (isPresent(taskDto)) return TaskTransformer.toDTO(taskRepo.getByName(taskDto.getName()));
            Task task = taskRepo.create(TaskTransformer.toEntity(taskDto));
            return toDTO(task);
        }
        return null;
    }

    @Transactional
    @Override
    public TaskDto update(TaskDto taskDto) {
        Task task = taskRepo.update(TaskTransformer.toEntity(taskDto));
        if (task == null)
            return null;
        return toDTO(task);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        taskRepo.delete(id);
    }

    @Transactional
    @Override
    public TaskDto getById(Long id) {
        Task task = taskRepo.read(id);
        TaskDto taskDto = toDTO(task);

        taskDto.setParentTaskDto(getParentTaskDto(taskDto.getId()));
        taskDto.setChildTaskDtoList(getSubTasks(taskDto.getId()));
        return taskDto;
    }

    @Transactional
    @Override
    public List<TaskDto> getSubTasks(Long id) {
        List<Task> tasks = taskRepo.getSubTasks(id);
        List<TaskDto> taskDtos = new ArrayList<>();
        for (Task task : tasks) {
            taskDtos.add(toDTO(task));
        }
        return taskDtos;
    }

    @Transactional
    @Override
    public TaskDto getParentTaskDto(Long parentId) {
        Task task = taskRepo.read(parentId);
        return toDTO(task);
    }

    @Transactional
    @Override
    public List<TaskDto> filterByOwner(Long ownerId) {
        List<TaskDto> taskDtoList = new ArrayList<>();
        return taskDtoList;
    }

        @Transactional
    @Override
    public List<TaskDto> filterByWorker(Long workerId) {
        List<TaskDto> taskDtoList = new ArrayList<>();
        return taskDtoList;
    }

        @Transactional
    @Override
    public List<TaskDto> filterByPriority(Long priorityId) {
        List<TaskDto> taskDtoList = new ArrayList<>();
        return taskDtoList;
    }

    @Transactional
    @Override
    public List<TaskDto> filterByStatus(Long statusId) {
        List<TaskDto> taskDtoList = new ArrayList<>();
        return taskDtoList;
    }

    @Override
    @Transactional
    public boolean isPresent(TaskDto taskDto) {
        return taskRepo.getByName(taskDto.getName()) != null;
    }

    @Override
    @Transactional
    public boolean isPresent(String name){return (taskRepo.getByName(name)!=null);}

    @Override
    @Transactional
    public TaskDto getByName(String name){
        return TaskTransformer.toDTO(taskRepo.getByName(name));
    }


    private boolean isMessageSent(UserDto userDto, String message, String subject) {
        return mailSender.sendMessage(userDto, message, subject);
    }
}
