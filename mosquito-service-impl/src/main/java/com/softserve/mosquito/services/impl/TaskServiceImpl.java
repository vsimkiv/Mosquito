package com.softserve.mosquito.services.impl;

import com.softserve.mosquito.dtos.TaskFullDto;
import com.softserve.mosquito.dtos.TaskSimpleDto;
import com.softserve.mosquito.dtos.UserDto;
import com.softserve.mosquito.entities.Estimation;
import com.softserve.mosquito.entities.Task;
import com.softserve.mosquito.repo.api.TaskRepo;
import com.softserve.mosquito.services.api.TaskService;
import com.softserve.mosquito.services.mail.MailSender;
import com.softserve.mosquito.transformer.CommentTransformer;
import com.softserve.mosquito.transformer.EstimationTransformer;
import com.softserve.mosquito.transformer.TaskTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.softserve.mosquito.transformer.TaskTransformer.toFullDTO;
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

    //CRUD methods. Made by VS
    @Transactional
    @Override
    public TaskFullDto save(TaskFullDto taskFullDto) {
        //TODO messaging exception "Could not convert socket to TLS..."
        /*if (isMessageSent(taskFullDto.getWorkerDto(),
                "You was assigned for this task" + taskFullDto.getName(),
                "Mosquito Task Manager")) {*/
        Task task = taskRepo.create(TaskTransformer.toEntity(taskFullDto));
        if (task == null)
            return null;
        return toFullDTO(task);
        //}
    }

    @Transactional
    @Override
    public TaskFullDto update(TaskFullDto taskFullDto) {
        Task task = taskRepo.update(TaskTransformer.toEntity(taskFullDto));
        if (task == null)
            return null;
        return toFullDTO(task);
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
        TaskFullDto taskFullDto = toFullDTO(task);

        //set parent
        Task parent = task.getParentTask();
        if (parent != null) {
            taskFullDto.setParentTaskFullDto(getParent(parent.getId()));
        }

        //set estimation
        Estimation estimation = task.getEstimation();
        if (estimation != null) {
            taskFullDto.setEstimationDto(EstimationTransformer.toDTO(estimation));
        }

        //set list of comments
        taskFullDto.setCommentDtoList(CommentTransformer.toDTOList(task.getComments()));
        //set child tasks
        taskFullDto.setChildTaskFullDtoList(getSubTasks(taskFullDto.getId()));
        return taskFullDto;
    }

    //Additional methods. Made by VS
    @Transactional
    @Override
    public TaskFullDto getParent(Long parentId) {
        return toFullDTO(taskRepo.read(parentId));
    }

    @Transactional
    @Override
    public List<TaskFullDto> getSubTasks(Long id) {
        return TaskTransformer.toDTOList(taskRepo.getSubTasks(id));
    }

    @Transactional
    @Override
    public List<TaskFullDto> getByOwner(Long ownerId){
        return TaskTransformer.toDTOList(taskRepo.getByOwner(ownerId));
    }

    @Transactional
    @Override
    public List<TaskFullDto> getByWorker(Long workerId){
        return TaskTransformer.toDTOList(taskRepo.getByOwner(workerId));
    }

    //Methods for project. Made by VS
    @Transactional
    @Override
    public List<TaskFullDto> getAllProjects() {
        return TaskTransformer.toDTOList(taskRepo.getAllProjects());
    }

    @Transactional
    @Override
    public List<TaskFullDto> getProjectsByOwner(Long ownerId) {
        return TaskTransformer.toDTOList(taskRepo.getProjectsByOwner(ownerId));
    }

    //Filter methods. Made by VS
    @Transactional
    @Override
    public List<TaskFullDto> filterByStatus(List<TaskFullDto> taskFullDtoList, Long statusId) {
        List<TaskFullDto> filteredList = new ArrayList<>();
        for (TaskFullDto taskDto: taskFullDtoList) {
            if (taskDto.getStatusDto().getId().equals(statusId)){
                filteredList.add(taskDto);
            }
        }
        return filteredList;
    }

    //methods for Trello. Made by Mark
    @Transactional
    @Override
    public TaskSimpleDto getSimpleTaskById(Long id) {
        Task task = taskRepo.read(id);
        return toSimpleDto(task);
    }

    @Transactional
    @Override
    public TaskFullDto getByTrelloId(String trelloId){
        return TaskTransformer.toFullDTO(taskRepo.getByTrelloId(trelloId));
    }

    @Override
    @Transactional
    public boolean isPresent(String trelloId) {
        return (taskRepo.getByTrelloId(trelloId) != null);
    }

    @Override
    @Transactional
    public TaskFullDto getByName(String name) {
        return TaskTransformer.toFullDTO(taskRepo.getByName(name));
    }

    private boolean isMessageSent(UserDto userDto, String message, String subject) {
        return mailSender.sendMessage(userDto, message, subject);
    }
}
