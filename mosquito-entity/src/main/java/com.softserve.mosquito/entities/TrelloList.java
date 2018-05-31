package com.softserve.mosquito.entities;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import java.util.Arrays;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TrelloList {

    private String name;
    private String id;
    private TrelloCard[] listOfTrelloCards;

    public TrelloList() {
    }

    public TrelloList(String name, String id, TrelloCard[] listOfTrelloCards) {
        this.name = name;
        this.id = id;
        this.listOfTrelloCards = listOfTrelloCards;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TrelloCard[] getListOfTrelloCards() {
        return listOfTrelloCards;
    }

    public void setListOfTrelloCards(TrelloCard[] listOfTrelloCards) {
        this.listOfTrelloCards = listOfTrelloCards;
    }

    @Override
    public String toString() {
        return "TrelloList{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", listOfTrelloCards=" + Arrays.toString(listOfTrelloCards) +
                '}';
    }
}
