package com.softserve.mosquito.services.impl;

import com.softserve.mosquito.dtos.*;
import com.softserve.mosquito.services.api.*;
import org.codehaus.jackson.map.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class TrelloCardServiceImpl implements TrelloCardService {

    private TrelloInfoService trelloInfoService;
    private TaskService taskService;
    private StatusService statusService;
    private UserService userService;

    // HARDCODE User********************************
    private Long userId; //= 44L;
    private TrelloInfoDto trelloInfo; // = trelloInfoService.getByUserId(userId);
    //*********************************************

    @Autowired
    public TrelloCardServiceImpl(TrelloInfoService trelloInfoService, TaskService taskService,
                                 StatusService statusService, UserService userService) {

        this.trelloInfoService = trelloInfoService;
        this.taskService = taskService;
        this.statusService = statusService;
        this.userService = userService;
        this.userId = 44L;
        this.trelloInfo = trelloInfoService.getByUserId(userId);
    }

    @Transactional
    @Override
    public void getTasksFromTrello() {

        for (TrelloBoardDto trelloBoard : getAllTrelloBoards()) {
            for (TrelloListDto trelloList : getTrelloListsByBoard(trelloBoard.getId())) {
                if (trelloList.getName().equalsIgnoreCase("todo")) {
                    createTasksFromTrelloCards(getTrelloCardsByList(trelloList.getId()), "todo", trelloBoard.getName());
                }
                if (trelloList.getName().equalsIgnoreCase("doing")) {
                    createTasksFromTrelloCards(getTrelloCardsByList(trelloList.getId()), "doing", trelloBoard.getName());
                }
                if (trelloList.getName().equalsIgnoreCase("done")) {
                    createTasksFromTrelloCards(getTrelloCardsByList(trelloList.getId()), "done", trelloBoard.getName());
                }
            }

        }
    }


    public List<TaskSimpleDto> showAllNewTrelloTasks() {
        List<TaskSimpleDto> trelloTasks = new ArrayList<TaskSimpleDto>();

        for (TrelloBoardDto trelloBoard : getAllTrelloBoards()) {
            for (TrelloListDto trelloList : getTrelloListsByBoard(trelloBoard.getId())) {

                if (trelloList.getName().equalsIgnoreCase("todo")) {
                    trelloTasks.addAll(CollectTaskSimpleDtosFromTrelloCards(getTrelloCardsByList(trelloList.getId()),
                            "todo", trelloBoard.getName()));
                }

                if (trelloList.getName().equalsIgnoreCase("doing")) {
                    trelloTasks.addAll(CollectTaskSimpleDtosFromTrelloCards(getTrelloCardsByList(trelloList.getId()),
                            "doing", trelloBoard.getName()));
                }
                if (trelloList.getName().equalsIgnoreCase("done")) {
                    trelloTasks.addAll(CollectTaskSimpleDtosFromTrelloCards(getTrelloCardsByList(trelloList.getId()),
                            "done", trelloBoard.getName()));
                }
            }

        }
        return trelloTasks;
    }


    private List<TaskSimpleDto> CollectTaskSimpleDtosFromTrelloCards(TrelloCardDto[] trelloCards, String status, String projectName) {

        List<TaskSimpleDto> trelloTasks = new ArrayList<TaskSimpleDto>();

        TaskSimpleDto taskDto = new TaskSimpleDto();
        taskDto.setName(projectName);
        taskDto.setOwner(userService.getById(trelloInfo.getUserDto().getId()).toString());
        taskDto.setWorker(userService.getById(trelloInfo.getUserDto().getId()).toString());
        if (!taskService.isPresent(projectName)) trelloTasks.add(taskDto);

        for (TrelloCardDto trelloCard : trelloCards) {

            TaskSimpleDto trelloTask = new TaskSimpleDto();
            trelloTask.setName(trelloCard.getName());
            trelloTask.setWorker(userService.getById(trelloInfo.getUserDto().getId()).toString());
            trelloTask.setOwner(userService.getById(trelloInfo.getUserDto().getId()).toString());
            trelloTask.setParentTask(projectName);
            trelloTask.setStatus(status);
            if (!taskService.isPresent(trelloCard.getName())) trelloTasks.add(trelloTask);
        }
        return trelloTasks;
    }

    private List<TaskFullDto> CollectTasksFromTrelloCards(TrelloCardDto[] trelloCards, String status, String projectName) {

        List<TaskFullDto> trelloTasks = new ArrayList<TaskFullDto>();

        TaskFullDto taskFullDto = new TaskFullDto();
        taskFullDto.setName(projectName);
        taskFullDto.setOwnerDto(userService.getById(trelloInfo.getUserDto().getId()));
        taskFullDto.setWorkerDto(userService.getById(trelloInfo.getUserDto().getId()));
        if (taskService.isPresent(taskFullDto)) taskFullDto = taskService.getByName(taskFullDto.getName());
        else trelloTasks.add(taskFullDto);

        for (TrelloCardDto trelloCard : trelloCards) {

            TaskFullDto trelloTask = new TaskFullDto();
            trelloTask.setName(trelloCard.getName());
            trelloTask.setWorkerDto(userService.getById(trelloInfo.getUserDto().getId()));
            trelloTask.setOwnerDto(userService.getById(trelloInfo.getUserDto().getId()));
            trelloTask.setParentTaskFullDto(taskFullDto);
            trelloTask.setStatusDto(statusService.getByName(status));
            if (!taskService.isPresent(trelloTask)) trelloTasks.add(trelloTask);
        }
        return trelloTasks;
    }

    private void createTasksFromTrelloCards(TrelloCardDto[] trelloCards, String status, String projectName) {

        TaskFullDto taskFullDto = new TaskFullDto();
        taskFullDto.setName(projectName);
        taskFullDto.setOwnerDto(userService.getById(trelloInfo.getUserDto().getId()));
        taskFullDto.setWorkerDto(userService.getById(trelloInfo.getUserDto().getId()));
        taskFullDto = taskService.save(taskFullDto);

        for (TrelloCardDto trelloCard : trelloCards) {
            TaskFullDto trelloTask = new TaskFullDto();
            trelloTask.setName(trelloCard.getName());
            trelloTask.setWorkerDto(userService.getById(trelloInfo.getUserDto().getId()));
            trelloTask.setOwnerDto(userService.getById(trelloInfo.getUserDto().getId()));
            trelloTask.setParentTaskFullDto(taskFullDto);
            trelloTask.setStatusDto(statusService.getByName(status));
            taskService.save(trelloTask);
        }
    }

    private TrelloBoardDto[] getAllTrelloBoards() {
        TrelloBoardDto[] trelloBoards = null;
        String urlGetAllBoards = String.format("https://trello.com/1/members/%s/boards?key=%s&token=%s",
                trelloInfo.getUserTrelloName(), trelloInfo.getUserTrelloKey(), trelloInfo.getUserTrelloToken());
        try {
            RestTemplate restTemplate = new RestTemplate();
            String responseAsString = restTemplate.getForObject(urlGetAllBoards, String.class);

            ObjectMapper mapper = new ObjectMapper();
            trelloBoards = mapper.readValue(responseAsString, TrelloBoardDto[].class);

        } catch (Exception e) {
            System.err.println(e);
        }
        return trelloBoards;
    }

    private TrelloListDto[] getTrelloListsByBoard(String idBoard) {
        TrelloListDto[] TrelloLists = null;
        String urlGetListOfBoard = String.format("https://trello.com/1/boards/%s/lists?cards=open&card_fields=name&fields=name&key=%s&token=%s",
                idBoard, trelloInfo.getUserTrelloKey(), trelloInfo.getUserTrelloToken());
        try {
            RestTemplate restTemplate = new RestTemplate();
            String responseAsString = restTemplate.getForObject(urlGetListOfBoard, String.class);

            ObjectMapper mapper = new ObjectMapper();
            TrelloLists = mapper.readValue(responseAsString, TrelloListDto[].class);

        } catch (Exception e) {
            System.err.println(e);
        }
        return TrelloLists;
    }

    private TrelloCardDto[] getTrelloCardsByList(String idList) {
        TrelloCardDto[] TrelloCards = null;

        String urlGetCardsByList = String.format("https://trello.com/1/lists/%s/cards?key=%s&token=%s",
                idList, trelloInfo.getUserTrelloKey(), trelloInfo.getUserTrelloToken());
        try {
            RestTemplate restTemplate = new RestTemplate();
            String responseAsString = restTemplate.getForObject(urlGetCardsByList, String.class);

            ObjectMapper mapper = new ObjectMapper();
            TrelloCards = mapper.readValue(responseAsString, TrelloCardDto[].class);

        } catch (Exception e) {
            System.err.println(e);
        }
        return TrelloCards;
    }
}

