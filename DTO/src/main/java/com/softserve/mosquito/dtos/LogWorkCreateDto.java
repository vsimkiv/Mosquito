package com.softserve.mosquito.dtos;

public class LogWorkCreateDto {
    private String description;
    private Long userId;
    private int logged;
    private Long estimationId;

    public LogWorkCreateDto() {
    }

    public LogWorkCreateDto(String description, Long userId, int logged, Long estimationId) {
        this.description = description;
        this.userId = userId;
        this.logged = logged;
        this.estimationId = estimationId;
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

    public Long getEstimationId() { return estimationId; }

    public void setEstimationId(Long estimationId) { this.estimationId = estimationId; }
}
