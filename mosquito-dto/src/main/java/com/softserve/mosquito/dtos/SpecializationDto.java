package com.softserve.mosquito.dtos;

/**
 * Generic DTO for Specialization Entity
 */
public class SpecializationDto {
    private Long id;
    private String title;

    public SpecializationDto() {
    }

    public SpecializationDto(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    public Long getId() { return id; }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
