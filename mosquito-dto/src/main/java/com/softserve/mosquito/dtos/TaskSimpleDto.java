package com.softserve.mosquito.dtos;

public class TaskSimpleDto {

    private Long id;
    private String name;
    private String parentTask;
    private String owner;
    private String worker;
    private String estimation;
    private String priority;
    private String status;

    public TaskSimpleDto() {
    }

    public TaskSimpleDto(Long id, String name, String parentTask, String owner, String worker, String estimation, String priority, String status) {
        this.id = id;
        this.name = name;
        this.parentTask = parentTask;
        this.owner = owner;
        this.worker = worker;
        this.estimation = estimation;
        this.priority = priority;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentTask() {
        return parentTask;
    }

    public void setParentTask(String parentTask) {
        this.parentTask = parentTask;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getWorker() {
        return worker;
    }

    public void setWorker(String worker) {
        this.worker = worker;
    }

    public String getEstimation() {
        return estimation;
    }

    public void setEstimation(String estimation) {
        this.estimation = estimation;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "TaskSimpleDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", parentTask='" + parentTask + '\'' +
                ", owner='" + owner + '\'' +
                ", worker='" + worker + '\'' +
                ", estimation='" + estimation + '\'' +
                ", priority='" + priority + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
