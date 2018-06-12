package com.softserve.mosquito.services.impl;

import com.softserve.mosquito.dtos.*;
import com.softserve.mosquito.entities.*;
import com.softserve.mosquito.services.api.StatusService;
import com.softserve.mosquito.services.api.TaskService;
import com.softserve.mosquito.services.api.TrelloInfoService;
import com.softserve.mosquito.services.api.UserService;
import com.softserve.mosquito.transformer.impl.TrelloInfoTransformer;
import org.codehaus.jackson.map.ObjectMapper;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import javax.ws.rs.core.Response;

@Service
public class TrelloCardServiceImpl {

    private TrelloInfoService trelloInfoService;
    private TaskService taskService;
    private StatusService statusService;
    private UserService userService;

    // HARDCODE User********************************
    private Long userId = 44L;
    private TrelloInfoDto trelloInfo = null;//trelloInfoService.getTrelloInfoByUserId(userId);
    //*********************************************

    @Autowired
    public TrelloCardServiceImpl(TrelloInfoService trelloInfoService, TaskService taskService,
                                 StatusService statusService, UserService userService) {

        this.trelloInfoService = trelloInfoService;
        this.taskService = taskService;
        this.statusService = statusService;
        this.userService = userService;
    }

    @Transactional
    public void getTasksFromTrello(){

        for (TrelloBoardDto trelloBoard : getAllTrelloBoards()){
            for (TrelloListDto trelloList : getTrelloListsByBoard(trelloBoard.getId())){
                if (trelloList.getName().equalsIgnoreCase("todo")){
                    createTasksFromTrelloCards(getTrelloCardsByList(trelloList.getId()), "todo", trelloBoard.getName());
                }
                if (trelloList.getName().equalsIgnoreCase("doing")){
                    createTasksFromTrelloCards(getTrelloCardsByList(trelloList.getId()), "doing", trelloBoard.getName());
                }
                if (trelloList.getName().equalsIgnoreCase("done")){
                    createTasksFromTrelloCards(getTrelloCardsByList(trelloList.getId()), "done", trelloBoard.getName());
                }
            }

        }
    }
   private void createTasksFromTrelloCards(TrelloCardDto[] trelloCards, String status, String projectName){

        TaskDto taskDto = new TaskDto();
        taskDto.setName(projectName);
        taskDto.setOwnerDto(userService.getUserById(trelloInfo.getUser().getId()));
        taskDto.setWorkerDto(userService.getUserById(trelloInfo.getUser().getId()));
        taskService.create(taskDto);

        for (TrelloCardDto trelloCard : trelloCards){
            TaskDto trelloTask = new TaskDto();
            trelloTask.setName(trelloCard.getName());
            trelloTask.setWorkerDto(userService.getUserById(trelloInfo.getUser().getId()));
            trelloTask.setOwnerDto(userService.getUserById(trelloInfo.getUser().getId()));
            trelloTask.setParentTaskDto(taskDto);
            trelloTask.setStatusDto(statusService.getStatusByName(status));
            taskService.create(trelloTask);
        }
   }

    private TrelloBoardDto[] getAllTrelloBoards(){
        TrelloBoardDto[] trelloBoards= null;
        String urlGetAllBoards = String.format("https://impl.trello.com/1/members/%s/boards?key=%s&token=%s",
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

    private TrelloListDto[] getTrelloListsByBoard(String idBoard){
        TrelloListDto[] TrelloLists = null;
        String urlGetListOfBoard = String.format("https://impl.trello.com/1/boards/%s/lists?cards=open&card_fields=name&fields=name&key=%s&token=%s",
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

        String urlGetCardsByList= String.format("https://impl.trello.com/1/lists/%s/cards?key=%s&token=%s",
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

