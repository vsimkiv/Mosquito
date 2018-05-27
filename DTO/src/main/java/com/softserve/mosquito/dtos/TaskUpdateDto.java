package com.softserve.mosquito.dtos;

public class TaskUpdateDto {
    private String name;
    private Long workerId;
    private Long estimation;
    private Long priorityId;
    private Long statusId;

    public TaskUpdateDto() {
    }

    public TaskUpdateDto(String name, Long workerId, Long estimation, Long priorityId, Long statusId) {
        this.name = name;
        this.workerId = workerId;
        this.estimation = estimation;
        this.priorityId = priorityId;
        this.statusId = statusId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getWorkerId() {
        return workerId;
    }

    public void setWorkerId(Long workerId) {
        this.workerId = workerId;
    }

    public Long getEstimation() {
        return estimation;
    }

    public void setEstimation(Long estimation) {
        this.estimation = estimation;
    }

    public Long getPriorityId() {
        return priorityId;
    }

    public void setPriorityId(Long priorityId) {
        this.priorityId = priorityId;
    }

    public Long getStatusId() {
        return statusId;
    }

    public void setStatusId(Long statusId) {
        this.statusId = statusId;
    }
}
