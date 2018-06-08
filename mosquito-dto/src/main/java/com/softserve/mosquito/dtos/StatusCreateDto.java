package com.softserve.mosquito.dtos;

import java.io.Serializable;

public class StatusCreateDto implements Serializable {
    private String title;

    public StatusCreateDto() {
    }

    public StatusCreateDto(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
