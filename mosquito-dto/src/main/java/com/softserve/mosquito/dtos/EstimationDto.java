package com.softserve.mosquito.dtos;

import java.util.List;

public class EstimationDto {
    private Long id;
    private Integer timeEstimation;
    private Integer remaining;
    private Long taskId;
    private List<LogWorkDto> logWorks;

    public EstimationDto() {}

    public EstimationDto(Long id, Integer timeEstimation, Integer remaining,Long taskId, List<LogWorkDto> logWorks) {
        this.id = id;
        this.timeEstimation = timeEstimation;
        this.remaining = remaining;
        this.taskId = taskId;
        this.logWorks = logWorks;
    }

    public Long getId() { return id; }

    public Integer getTimeEstimation() { return timeEstimation; }
    public Integer getRemaining() { return remaining; }
    public Long getTaskId() { return taskId; }
    public List<LogWorkDto> getLogWorks() { return logWorks; }

    public void setTimeEstimation(Integer timeEstimation) { this.timeEstimation = timeEstimation; }
    public void setRemaining(Integer remaining) { this.remaining = remaining; }
    public void setTaskId(Long taskId) { this.taskId = taskId; }
    public void setLogWorks(List<LogWorkDto> logWorks) { this.logWorks = logWorks; }
}
