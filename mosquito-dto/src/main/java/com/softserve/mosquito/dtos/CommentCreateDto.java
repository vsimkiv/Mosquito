package com.softserve.mosquito.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.softserve.mosquito.entities.Task;
import com.softserve.mosquito.entities.User;

/*
 * DTO for creating comments
 */
public class CommentCreateDto {
    private Long id;
    private String text;
    @JsonIgnore
    private Task task;
    @JsonIgnore
    private User author;

    public CommentCreateDto() {
    }

    public CommentCreateDto(Long id, String text, Task task, User author) {
        this.id = id;
        this.text = text;
        this.task = task;
        this.author = author;
    }

    public CommentCreateDto(String text, Task task, User author) {
        this.text = text;
        this.task = task;
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "CommentCreateDto{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", task=" + task +
                ", author=" + author +
                '}';
    }
}
