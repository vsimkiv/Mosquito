package com.softserve.mosquito.dtos;

public class LogWorkCreateDto {
    private String description;
    private Long userId;
    private int logged;
    private int remaining;

    public LogWorkCreateDto() {
    }

    public LogWorkCreateDto(String description, Long userId, int logged, int remaining) {
        super();
        this.description = description;
        this.userId = userId;
        this.logged = logged;
        this.remaining = remaining;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getLogged() {
        return logged;
    }

    public void setLogged(int logged) {
        this.logged = logged;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public int getRemaining() {
        return remaining;
    }

    public void setRemaining(int remaining) {
        this.remaining = remaining;
    }


}
