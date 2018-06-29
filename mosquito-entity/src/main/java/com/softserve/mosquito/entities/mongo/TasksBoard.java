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
    private List<TaskMongo> taskMongos = new ArrayList<>();

    public TasksBoard() { }

    public TasksBoard(Long userId, List<TaskMongo> taskMongos) {
        this.userId = userId;
        this.taskMongos = taskMongos;
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

    public List<TaskMongo> getTaskMongos() {
        return taskMongos;
    }

    public void setTaskMongos(List<TaskMongo> taskMongos) {
        this.taskMongos = taskMongos;
    }
}
