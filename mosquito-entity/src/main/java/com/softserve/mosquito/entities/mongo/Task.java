package com.softserve.mosquito.entities.mongo;

public class Task {

    private Long taskId;
    private String taskName;

    public Task() {}

    public Task(Long taskId, String taskName) {
        this.taskId = taskId;
        this.taskName = taskName;
    }

    public Task(com.softserve.mosquito.entities.Task task) {
        this.taskId = task.getId();
        this.taskName = task.getName();
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }
}
