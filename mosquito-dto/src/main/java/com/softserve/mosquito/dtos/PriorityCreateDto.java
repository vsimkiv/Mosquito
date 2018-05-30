package com.softserve.mosquito.dtos;

public class PriorityCreateDto {
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
