package com.softserve.mosquito.dtos;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TrelloListDto {

    private String name;
    private String id;

    public TrelloListDto() {
    }

    public TrelloListDto(String name, String id) {
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
        return "TrelloListDto{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
