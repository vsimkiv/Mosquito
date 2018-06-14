package com.softserve.mosquito.services.impl;

import com.softserve.mosquito.dtos.*;
import com.softserve.mosquito.services.api.*;
import com.softserve.mosquito.transformer.TaskTransformer;
import org.codehaus.jackson.map.ObjectMapper;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import javax.ws.rs.core.Response;
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

    private List<TaskDto> CollectTasksFromTrelloCards(TrelloCardDto[] trelloCards, String status, String projectName) {

        List<TaskDto> trelloTasks = new ArrayList<TaskDto>();

        TaskDto taskDto = new TaskDto();
        taskDto.setName(projectName);
        taskDto.setOwnerDto(userService.getById(trelloInfo.getUserDto().getId()));
        taskDto.setWorkerDto(userService.getById(trelloInfo.getUserDto().getId()));
        if (taskService.isPresent(taskDto)) taskDto = taskService.getByName(taskDto.getName());
        else trelloTasks.add(taskDto);

        for (TrelloCardDto trelloCard : trelloCards) {

            TaskDto trelloTask = new TaskDto();
            trelloTask.setName(trelloCard.getName());
            trelloTask.setWorkerDto(userService.getById(trelloInfo.getUserDto().getId()));
            trelloTask.setOwnerDto(userService.getById(trelloInfo.getUserDto().getId()));
            trelloTask.setParentTaskDto(taskDto);
            trelloTask.setStatusDto(statusService.getByName(status));
            if (!taskService.isPresent(trelloTask)) trelloTasks.add(trelloTask);
        }
        return trelloTasks;
    }

    private void createTasksFromTrelloCards(TrelloCardDto[] trelloCards, String status, String projectName){

        TaskDto taskDto = new TaskDto();
        taskDto.setName(projectName);
        taskDto.setOwnerDto(userService.getById(trelloInfo.getUserDto().getId()));
        taskDto.setWorkerDto(userService.getById(trelloInfo.getUserDto().getId()));
        taskDto = taskService.save(taskDto);

        for (TrelloCardDto trelloCard : trelloCards){
            TaskDto trelloTask = new TaskDto();
            trelloTask.setName(trelloCard.getName());
            trelloTask.setWorkerDto(userService.getById(trelloInfo.getUserDto().getId()));
            trelloTask.setOwnerDto(userService.getById(trelloInfo.getUserDto().getId()));
            trelloTask.setParentTaskDto(taskDto);
            trelloTask.setStatusDto(statusService.getByName(status));
            taskService.save(trelloTask);
        }
   }

    private TrelloBoardDto[] getAllTrelloBoards(){
        TrelloBoardDto[] trelloBoards= null;
        String urlGetAllBoards = String.format("https://trello.com/1/members/%s/boards?key=%s&token=%s",
                trelloInfo.getUserTrelloName(), trelloInfo.getUserTrelloKey(), trelloInfo.getUserTrelloToken());

        try {
            ResteasyClient client = new ResteasyClientBuilder().build();
            ResteasyWebTarget target = client.target(urlGetAllBoards);

            Response response = target.request().get();

            String responseAsString = response.readEntity(String.class);

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

            ResteasyClient client = new ResteasyClientBuilder().build();
            ResteasyWebTarget target = client.target(urlGetListOfBoard);

            Response response = target.request().get();

            String responseAsString = response.readEntity(String.class);

            ObjectMapper mapper = new ObjectMapper();

            TrelloLists = mapper.readValue(responseAsString, TrelloListDto[].class);

        } catch (Exception e) {
            System.err.println(e);
        }

        return TrelloLists;
    }

    private TrelloCardDto[] getTrelloCardsByList(String idList){
        TrelloCardDto[] TrelloCards = null;

        String urlGetCardsByList= String.format("https://trello.com/1/lists/%s/cards?key=%s&token=%s",
                idList, trelloInfo.getUserTrelloKey(), trelloInfo.getUserTrelloToken());

        try {

            ResteasyClient client = new ResteasyClientBuilder().build();
            ResteasyWebTarget target = client.target(urlGetCardsByList);

            Response response = target.request().get();

            String responseAsString = response.readEntity(String.class);

            ObjectMapper mapper = new ObjectMapper();

            TrelloCards = mapper.readValue(responseAsString, TrelloCardDto[].class);

        } catch (Exception e) {
            System.err.println(e);
        }

        return TrelloCards;
    }

}

