package com.softserve.mosquito.services;

import com.softserve.mosquito.dtos.*;
import com.softserve.mosquito.dtos.TaskDto;
import com.softserve.mosquito.entities.Task;
import com.softserve.mosquito.services.impl.TaskServiceImpl;
import org.codehaus.jackson.map.ObjectMapper;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import javax.ws.rs.core.Response;

public class CardService {

    private Long userID = 1L;
    private String userName = "if086softserve";
    private String userKey = "9097df69617e33b2dd4d9fe573570eac";
    private String userToken = "b30aab2aa99bd68be5f45032ce63c5568363b2bac0a5c6088743270acdb02493";

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }


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
        taskCreateDto.setOwnerId(getUserID());
        taskCreateDto.setWorkerId(getUserID());
        Task task = taskService.createTask(taskCreateDto);

        for (Card card : cards){
            TaskDto trelloTask = new TaskDto();
            trelloTask.setName(card.getName());
            trelloTask.setWorkerId(getUserID());
            trelloTask.setOwnerId(getUserID());
            trelloTask.setParentId(task.getId());
            taskService.createTask(trelloTask);
        }
    }

    private Board[] getAllBoards(){
        Board[] boards= null;
        String urlGetAllBoards = String.format("https://impl.trello.com/1/members/%s/boards?key=%s&token=%s",
                userName, userKey, userToken);

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
                idBoard, userKey, userToken);

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
                idList, userKey, userToken);

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
