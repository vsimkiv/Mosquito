package com.softserve.mosquito.entities.mongo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "TasksBoard")
public class TasksBoard {

    @Id
    private String id;
    private Long userId;
    private List<Task> tasks = new ArrayList<>();

    public TasksBoard() { }

    public TasksBoard(Long userId, List<Task> tasks) {
        this.userId = userId;
        this.tasks = tasks;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}
