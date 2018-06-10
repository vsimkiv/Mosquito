package com.softserve.mosquito.dtos;

import java.time.LocalDateTime;

public class LogWorkDto {
    private Long id;
    private String description;
    private Long userId;
    private int logged;
    private LocalDateTime lastUpdate;
    private Long estimationId;

    public LogWorkDto() {
    }

    public LogWorkDto(Long id, String description, Long userId, int logged,LocalDateTime lastUpdate, Long estimationId) {
        this.id = id;
        this.description = description;
        this.userId = userId;
        this.logged = logged;
        this.lastUpdate = lastUpdate;
        this.estimationId = estimationId;
    }

    public Long getId() {  return id;  }

    public void setId(Long id) {   this.id = id; }

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

    public LocalDateTime getLastUpdate() { return lastUpdate; }

    public void setLastUpdate(LocalDateTime lastUpdate) { this.lastUpdate = lastUpdate; }

    public Long getEstimationId() { return estimationId; }

    public void setEstimationId(Long estimationId) { this.estimationId = estimationId; }

    public static class LogWorkBuilder {
        private LogWorkDto logWorkDto;

        private LogWorkBuilder() { logWorkDto = new LogWorkDto();}

        public LogWorkBuilder id(long id) { logWorkDto.id = id;
            return this;  }

        public LogWorkBuilder description(String description) {
            logWorkDto.description = description;
            return this;  }

        public LogWorkBuilder userId(Long userId) {
            logWorkDto.userId = userId;
            return this;   }

        public LogWorkBuilder logged(Integer logged) {
            logWorkDto.logged = logged;
            return this;  }

        public LogWorkBuilder estimationId(Long estimationId) {
            logWorkDto.estimationId = estimationId;
            return this;   }

        public LogWorkBuilder lastUpdate(LocalDateTime lastUpdate) {
            logWorkDto.lastUpdate = lastUpdate;
            return this;  }

        public LogWorkDto build(){
            return logWorkDto ;
        }

        }

    @Override
    public String toString() {
        return "LogWorkDto{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", userId=" + userId +
                ", logged=" + logged +
                ", lastUpdate=" + lastUpdate.toString() +
                ", estimationId=" + estimationId +
                '}';
    }
}
