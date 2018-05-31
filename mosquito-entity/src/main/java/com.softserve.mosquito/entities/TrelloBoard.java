package com.softserve.mosquito.entities;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class TrelloBoard {

    private String name;
    private String id;
    private String descData;
    private String desc;

    public TrelloBoard() {
    }

    public TrelloBoard(String name, String id, String descData, String desc) {
        this.name = name;
        this.id = id;
        this.descData = descData;
        this.desc = desc;
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

    public String getDescData() {
        return descData;
    }

    public void setDescData(String descData) {
        this.descData = descData;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "TrelloBoard{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", descData='" + descData + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}
