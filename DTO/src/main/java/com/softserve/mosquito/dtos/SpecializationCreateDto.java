package com.softserve.mosquito.dtos;

public class SpecializationCreateDto {
    private String title;

    public SpecializationCreateDto() {
    }

    public SpecializationCreateDto(String title) {
        super();
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
