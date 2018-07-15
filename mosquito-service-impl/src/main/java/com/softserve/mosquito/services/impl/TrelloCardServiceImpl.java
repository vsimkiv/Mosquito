package com.softserve.mosquito.services.impl;

import com.softserve.mosquito.dtos.*;
import com.softserve.mosquito.entities.mongo.TaskMongo;
import com.softserve.mosquito.services.api.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class TrelloCardServiceImpl implements TrelloCardService {

    private static final Logger LOGGER = LogManager.getLogger(TrelloCardServiceImpl.class);

    private TrelloInfoService trelloInfoService;
    private TaskService taskService;
    private StatusService statusService;
    private TrelloInfoDto trelloInfo;
    private TasksBoardService tasksBoardService;

    @Autowired
    public TrelloCardServiceImpl(TrelloInfoService trelloInfoService, TaskService taskService,
                                 StatusService statusService, TasksBoardService tasksBoardService) {
        this.trelloInfoService = trelloInfoService;
        this.taskService = taskService;
        this.statusService = statusService;
        this.tasksBoardService = tasksBoardService;
    }

    @Override
    public List<TaskCreateDto> getAllNewTrelloTasksOnFront(Long userId) {

        trelloInfo = trelloInfoService.getByUserId(userId);
        List<TaskCreateDto> trelloTasks = new ArrayList<>();

        for (TrelloBoardDto trelloBoard : getAllTrelloBoards()) {
            for (TrelloListDto trelloList : getTrelloListsByBoard(trelloBoard.getId())) {

                String listName = trelloList.getName();
                if (listName.equalsIgnoreCase("todo")
                        || listName.equalsIgnoreCase("doing")
                        || listName.equalsIgnoreCase("done")) {

                    TaskCreateDto taskCreateDto = TaskCreateDto.builder()
                            .name(trelloBoard.getName())
                            .ownerId(userId)
                            .workerId(userId)
                            .statusId(statusService.getByName(listName).getId())
                            .trelloId(trelloBoard.getId())
                            .priorityId(1L)
                            .estimationTime(0).build();

                    TaskDto taskDto;

                    if (!taskService.isPresent(taskCreateDto.getTrelloId())) taskDto = taskService.save(taskCreateDto);
                    else taskDto = taskService.getByTrelloId(taskCreateDto.getTrelloId());

                    taskCreateDto.setId(taskDto.getId());

                    if (!taskService.isPresent(taskCreateDto.getTrelloId())
                            && !taskCreateDto.isPresentInCollection(trelloTasks)) trelloTasks.add(taskCreateDto);

                    trelloTasks.addAll(collectTaskCreateDtosFromTrelloCards(getTrelloCardsByList(trelloList.getId()),
                            trelloList.getName().toLowerCase(), taskCreateDto, userId));
                }
            }
        }
        return trelloTasks;
    }

    @Override
    @Transactional
    public void createTasksFromTrello(Long userId) {
        trelloInfo = trelloInfoService.getByUserId(userId);
        createTrelloTasks(getAllNewTrelloTasksOnFront(userId));
    }

    @Override
    @Transactional
    public void createChosenTasksFromTrelloJSON(Long userId, List<TaskCreateDto> taskCreateDtoList) {
        trelloInfo = trelloInfoService.getByUserId(userId);
        createTrelloTasks(taskCreateDtoList);
    }

    //private methods
    private List<TaskCreateDto> collectTaskCreateDtosFromTrelloCards(
            TrelloCardDto[] trelloCards, String status, TaskCreateDto project, Long userId) {

        List<TaskCreateDto> taskCreateDtos = new ArrayList<>();

        for (TrelloCardDto trelloCard : trelloCards) {
            TaskCreateDto taskCreateDto = TaskCreateDto.builder()
                    .name(trelloCard.getName())
                    .ownerId(userId)
                    .workerId(userId)
                    .statusId(statusService.getByName(status).getId())
                    .parentId(project.getId())
                    .trelloId(trelloCard.getId())
                    .estimationTime(0)
                    .priorityId(1L).build();

            if (!taskService.isPresent(trelloCard.getId())) taskCreateDtos.add(taskCreateDto);
        }
        return taskCreateDtos;
    }

    private void createTrelloTasks(List<TaskCreateDto> taskCreateDtoList) {
        for (TaskCreateDto taskCreateDto : taskCreateDtoList) {
            TaskDto task = taskService.save(taskCreateDto);
            tasksBoardService.add(new TaskMongo(task.getId(),task.getName(),task.getStatus().getId()),
                    task.getWorkerId());
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
            LOGGER.error("Problem with getAllTrelloBoards method" + Arrays.toString(e.getStackTrace()));
            return new TrelloBoardDto[0];
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
            LOGGER.error("Problem with getTrelloListsByBoard method" + Arrays.toString(e.getStackTrace()));
            return new TrelloListDto[0];
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
            LOGGER.error("Problem with getTrelloCardsByList method" + Arrays.toString(e.getStackTrace()));
            return new TrelloCardDto[0];
        }
        return trelloCards;
    }
}