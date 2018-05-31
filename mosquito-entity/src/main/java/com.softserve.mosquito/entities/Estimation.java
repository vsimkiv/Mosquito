package com.softserve.mosquito.entities;

import java.io.Serializable;
import java.util.List;

public class Estimation implements Serializable {
    private Long id;
    private Integer timeEstimation;
    private Integer remaining;
    private List<LogWork> logWorks;

    public Estimation() {}

    public Estimation(int timeEstimation) { this.timeEstimation = timeEstimation;}


    public Estimation(Long id, Integer timeEstimation, Integer remaining) {
        this.timeEstimation = timeEstimation;
        this.id = id;
        this.remaining = remaining;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTimeEstimation() {
        return timeEstimation;
    }

    public void setTimeEstimation(Integer timeEstimation) {
        this.timeEstimation = timeEstimation;
    }

    public Integer getRemaining() {
        return remaining;
    }

    public void setRemaining(Integer remaining) {
        this.remaining = remaining;
    }

    public List<LogWork> getLogWorks() {
        return logWorks;
    }

    public void setLogWorks(List<LogWork> logWorks) {
        this.logWorks = logWorks;
    }

    @Override
    public String toString() {
        return "Estimation{" +
                "id=" + id +
                ", timeEstimation=" + timeEstimation +
                ", remaining=" + remaining +
                ", logWorks=" + logWorks +
                '}';
    }
}
