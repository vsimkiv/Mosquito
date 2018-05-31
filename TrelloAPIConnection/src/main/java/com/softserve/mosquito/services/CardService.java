package com.softserve.mosquito.services;

import com.softserve.mosquito.dtos.*;
import com.softserve.mosquito.dtos.TaskDto;

import com.softserve.mosquito.entities.Task;
import com.softserve.mosquito.entities.TrelloInfo;

import com.softserve.mosquito.services.impl.StatusServiceImpl;
import com.softserve.mosquito.services.impl.TaskServiceImpl;
import com.softserve.mosquito.services.impl.TrelloInfoServiceImpl;

import org.codehaus.jackson.map.ObjectMapper;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import javax.ws.rs.core.Response;

public class CardService {

    private Long userId = 44L;
    private TrelloInfo trelloInfo = new TrelloInfoServiceImpl().getTrelloInfoByUserId(userId);


    public void getTasksFromTrello(){

        for (Board board : getAllBoards()){
            for (List list : getListByBoard(board.getId())){
                if (list.getName().equalsIgnoreCase("todo")){
                    createTasksFromCards(getCardsByList(list.getId()), "todo", board.getName());
                }
                if (list.getName().equalsIgnoreCase("doing")){
                    createTasksFromCards(getCardsByList(list.getId()), "doing", board.getName());
                }
                if (list.getName().equalsIgnoreCase("done")){
                    createTasksFromCards(getCardsByList(list.getId()), "done", board.getName());
                }
            }

        }
    }

    private void createTasksFromCards(Card[] cards, String status, String projectName){

        TaskServiceImpl taskService = new TaskServiceImpl();
        TaskDto taskCreateDto = new TaskDto();
        taskCreateDto.setName(projectName);
        taskCreateDto.setOwnerId(trelloInfo.getUserId());
        taskCreateDto.setWorkerId(trelloInfo.getUserId());
        Task task = taskService.createTask(taskCreateDto);

        for (Card card : cards){
            TaskDto trelloTask = new TaskDto();
            trelloTask.setName(card.getName());
            trelloTask.setWorkerId(trelloInfo.getUserId());
            trelloTask.setOwnerId(trelloInfo.getUserId());
            trelloTask.setParentId(task.getId());
            trelloTask.setStatusId(new StatusServiceImpl().getStatusByName(status).getId());
            taskService.createTask(trelloTask);

        }
    }

    private Board[] getAllBoards(){
        Board[] boards= null;
        String urlGetAllBoards = String.format("https://impl.trello.com/1/members/%s/boards?key=%s&token=%s",
                trelloInfo.getUserTrelloName(), trelloInfo.getUserTrelloKey(), trelloInfo.getUserTrelloToken());

        try {
            ResteasyClient client = new ResteasyClientBuilder().build();
            ResteasyWebTarget target = client.target(urlGetAllBoards);

            Response response = target.request().get();

            String responseAsString = response.readEntity(String.class);

            ObjectMapper mapper = new ObjectMapper();

            boards = mapper.readValue(responseAsString, Board[].class);

        } catch (Exception e) {
            System.err.println(e);
        }

        return boards;
    }

    private List[] getListByBoard(String idBoard){
        List[] lists = null;
        String urlGetListOfBoard = String.format("https://impl.trello.com/1/boards/%s/lists?cards=open&card_fields=name&fields=name&key=%s&token=%s",
                idBoard, trelloInfo.getUserTrelloKey(), trelloInfo.getUserTrelloToken());

        try {

            ResteasyClient client = new ResteasyClientBuilder().build();
            ResteasyWebTarget target = client.target(urlGetListOfBoard);

            Response response = target.request().get();

            String responseAsString = response.readEntity(String.class);

            ObjectMapper mapper = new ObjectMapper();

            lists = mapper.readValue(responseAsString, List[].class);

        } catch (Exception e) {
            System.err.println(e);
        }

        return lists;
    }

    private Card[] getCardsByList(String idList){
        Card[] cards = null;

        String urlGetCardsByList= String.format("https://impl.trello.com/1/lists/%s/cards?key=%s&token=%s",
                idList, trelloInfo.getUserTrelloKey(), trelloInfo.getUserTrelloToken());

        try {

            ResteasyClient client = new ResteasyClientBuilder().build();
            ResteasyWebTarget target = client.target(urlGetCardsByList);

            Response response = target.request().get();

            String responseAsString = response.readEntity(String.class);

            ObjectMapper mapper = new ObjectMapper();

            cards = mapper.readValue(responseAsString, Card[].class);

        } catch (Exception e) {
            System.err.println(e);
        }

        return cards;

    }
}
