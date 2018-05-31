package com.softserve.mosquito.dtos;

public class StatusCreateDto {
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
