package com.softserve.mosquito.dtos;

public class TaskDto {
    private String name;
    private Long parentId;
    private Long ownerId;
    private Long workerId;
    private Integer estimation;
    private Integer remaining;
    private Byte priorityId;
    private Byte statusId;
    private String assigneeFirstName;
    private String assigneeLastName;
    private String priorityTitle;
    private String statusTitle;

    public TaskDto() {
    }

    public TaskDto(String name, Long parentId, Long ownerId, Long workerId, Integer estimation, Byte priorityId,
                   Byte statusId) {
        super();
        this.name = name;
        this.parentId = parentId;
        this.ownerId = ownerId;
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

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public Long getWorkerId() {
        return workerId;
    }

    public void setWorkerId(Long workerId) {
        this.workerId = workerId;
    }

    public Integer getEstimation() {
        return estimation;
    }

    public void setEstimation(Integer estimation) {
        this.estimation = estimation;
    }

    public Byte getPriorityId() {
        return priorityId;
    }

    public void setPriorityId(Byte priorityId) {
        this.priorityId = priorityId;
    }

    public Byte getStatusId() {
        return statusId;
    }

    public void setStatusId(Byte statusId) {
        this.statusId = statusId;
    }

    public String getAssigneeFirstName() { return assigneeFirstName; }

    public void setAssigneeFirstName(String assigneeFirstName) { this.assigneeFirstName = assigneeFirstName; }

    public String getAssigneeLastName() { return assigneeLastName; }

    public void setAssigneeLastName(String assigneeLastName) { this.assigneeLastName = assigneeLastName; }

    public String getPriorityTitle() { return priorityTitle; }

    public void setPriorityTitle(String priorityTitle) { this.priorityTitle = priorityTitle; }

    public String getStatusTitle() { return statusTitle; }

    public void setStatusTitle(String statusTitle) { this.statusTitle = statusTitle; }

    public Integer getRemaining() { return remaining; }

    public void setRemaining(Integer remaining) { this.remaining = remaining; }
}
