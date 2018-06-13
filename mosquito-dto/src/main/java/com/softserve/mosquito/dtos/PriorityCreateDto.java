package com.softserve.mosquito.dtos;

import java.io.Serializable;

public class PriorityCreateDto implements Serializable{
    private String title;

    public PriorityCreateDto() {
    }

    public PriorityCreateDto(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
