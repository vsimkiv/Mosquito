package com.softserve.mosquito.dtos;

/**
 * Generic DTO for Priority Entity
 */
public class PriorityDto {
    private Byte id;
    private String title;

    public PriorityDto() {
    }

    public PriorityDto(Byte id, String title) {
        this.id = id;
        this.title = title;
    }

    public Byte getId() {
        return id;
    }

    public void setId(Byte id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
