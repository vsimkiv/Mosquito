package com.softserve.mosquito.dtos;

/**
 * Generic DTO for Specialization Entity
 */
public class SpecializationDto {
    private Byte id;
    private String title;

    public SpecializationDto() {
    }

    public SpecializationDto(Byte id, String title) {
        this.id = id;
        this.title = title;
    }

    public Byte getId() { return id; }

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
