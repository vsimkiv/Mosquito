package com.softserve.mosquito.controllers;

import javax.ws.rs.core.Response;

import com.softserve.mosquito.dtos.Board;
import com.softserve.mosquito.dtos.Card;
import com.softserve.mosquito.dtos.List;

import com.sun.javafx.binding.StringFormatter;
import org.codehaus.jackson.map.ObjectMapper;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

public class BoardController {

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

    public Board[] getAllBoards(){
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

    public List[] getListByBoard(String idBoard){
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

    public Card[] getCardsByList(String idList){
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
