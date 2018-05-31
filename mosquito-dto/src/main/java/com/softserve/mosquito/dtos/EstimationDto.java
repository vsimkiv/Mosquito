package com.softserve.mosquito.dtos;

import java.util.List;

public class EstimationDto {
    private Long id;
    private Integer timeEstimation;
    private Integer remaining;
    private List<LogWorkDto> logWorks;

    public EstimationDto() {}

    public EstimationDto(Integer timeEstimation, Integer remaining, List<LogWorkDto> logWorks) {
        this.timeEstimation = timeEstimation;
        this.remaining = remaining;
        this.logWorks = logWorks;
    }

    public Long getId() { return id; }
    public Integer getTimeEstimation() { return timeEstimation; }
    public Integer getRemaining() { return remaining; }
    public List<LogWorkDto> getLogWorks() { return logWorks; }

    public void setTimeEstimation(Integer timeEstimation) { this.timeEstimation = timeEstimation; }
    public void setRemaining(Integer remaining) { this.remaining = remaining; }
    public void setLogWorks(List<LogWorkDto> logWorks) { this.logWorks = logWorks; }
}
