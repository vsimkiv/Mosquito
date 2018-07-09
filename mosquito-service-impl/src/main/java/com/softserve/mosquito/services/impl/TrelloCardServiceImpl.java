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
    private TrelloInfoDto trelloInfo;

    @Autowired
    public TrelloCardServiceImpl(TrelloInfoService trelloInfoService, TaskService taskService,
                                 StatusService statusService, UserService userService) {

        this.trelloInfoService = trelloInfoService;
        this.taskService = taskService;
        this.statusService = statusService;

    }

    @Override
    @Transactional
    public List<TaskCreateDto> getAllNewTrelloTasksOnFront(Long userId) {
        trelloInfo = trelloInfoService.getByUserId(userId);

        List<TaskCreateDto> trelloTasks = new ArrayList<>();

        for (TrelloBoardDto trelloBoard : getAllTrelloBoards()) {
            for (TrelloListDto trelloList : getTrelloListsByBoard(trelloBoard.getId())) {
                if (trelloList.getName().equalsIgnoreCase("todo") ||
                        trelloList.getName().equalsIgnoreCase("doing") ||
                        trelloList.getName().equalsIgnoreCase("done")) {

                    TaskCreateDto taskCreateDto = new TaskCreateDto(trelloBoard.getName(), userId, userId,
                            statusService.getByName("todo").getId(),null, trelloBoard.getId());
                    taskCreateDto.setEstimationTime(11111);
                    taskCreateDto.setPriorityId(1L);

                    if (!taskService.isPresent(taskCreateDto.getTrelloId())) {
                        trelloTasks.add(taskCreateDto);
                        taskService.save(taskCreateDto);
                    }


                    trelloTasks.addAll(collectTaskCreateDtosFromTrelloCards(getTrelloCardsByList(trelloList.getId()),
                            trelloList.getName().toLowerCase(), trelloBoard.getId(), userId));
                }
            }
        }
        return trelloTasks;
    }

    @Override
    @Transactional
    public void createTasksFromTrello(Long userId) {
        trelloInfo = trelloInfoService.getByUserId(userId);
        createTrelloTasks(userId, getAllNewTrelloTasksOnFront(userId));
    }

    @Override
    @Transactional
    public void createChosenTasksFromTrelloJSON(Long userId, List<TaskCreateDto> taskCreateDtoList) {
        trelloInfo = trelloInfoService.getByUserId(userId);
        createTrelloTasks(userId, taskCreateDtoList);
    }

    //private methods
    private List<TaskCreateDto> collectTaskCreateDtosFromTrelloCards(
            TrelloCardDto[] trelloCards, String status, String projectId, Long userId) {

        List<TaskCreateDto> taskCreateDtos = new ArrayList<>();

        for (TrelloCardDto trelloCard : trelloCards) {
            TaskCreateDto taskCreateDto = new TaskCreateDto(trelloCard.getName(), userId, userId,
                    statusService.getByName(status).getId(),
                    taskService.getByTrelloId(projectId).getId() , trelloCard.getId());

            if (!taskService.isPresent(trelloCard.getId())) taskCreateDtos.add(taskCreateDto);
        }
        return taskCreateDtos;
    }

    private void createTrelloTasks(Long userId, List<TaskCreateDto> taskCreateDtoList) {
        for (TaskCreateDto taskCreateDto : taskCreateDtoList) {
            taskCreateDto.setOwnerId(userId);
            taskCreateDto.setWorkerId(userId);
            taskService.save(taskCreateDto);
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
        TrelloListDto[] trelloLists = null;
        String urlGetListOfBoard = String.format("https://trello.com/1/boards/%s/lists?cards=open&card_fields=name&fields=name&key=%s&token=%s",
                idBoard, trelloInfo.getUserTrelloKey(), trelloInfo.getUserTrelloToken());
        try {
            RestTemplate restTemplate = new RestTemplate();
            String responseAsString = restTemplate.getForObject(urlGetListOfBoard, String.class);

            ObjectMapper mapper = new ObjectMapper();
            trelloLists = mapper.readValue(responseAsString, TrelloListDto[].class);

        } catch (Exception e) {
            System.err.println(e);
        }
        return trelloLists;
    }

    private TrelloCardDto[] getTrelloCardsByList(String idList) {
        TrelloCardDto[] trelloCards = null;

        String urlGetCardsByList = String.format("https://trello.com/1/lists/%s/cards?key=%s&token=%s",
                idList, trelloInfo.getUserTrelloKey(), trelloInfo.getUserTrelloToken());
        try {
            RestTemplate restTemplate = new RestTemplate();
            String responseAsString = restTemplate.getForObject(urlGetCardsByList, String.class);

            ObjectMapper mapper = new ObjectMapper();
            trelloCards = mapper.readValue(responseAsString, TrelloCardDto[].class);

        } catch (Exception e) {
            System.err.println(e);
        }
        return trelloCards;
    }
}

