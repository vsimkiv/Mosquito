package com.softserve.mosquito.dtos;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TrelloCardDto {

    private String name;
    private String id;

    public TrelloCardDto() {
    }

    public TrelloCardDto(String name, String id) {
        this.name = name;
        this.id = id;
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

    @Override
    public String toString() {
        return "TrelloCardDto{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
