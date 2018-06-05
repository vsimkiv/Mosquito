package com.softserve.mosquito.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
public class Comment implements Serializable {

    @Id
    @GeneratedValue (strategy=GenerationType.IDENTITY)
    private Long id;
    private String text;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

    @Column(name = "last_update")
    private LocalDateTime lastUpdate;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;

    public Comment() {
    }

    //transform from DTO
    /*public Comment(String text, Long taskId, Long authorId) {

        this.text = text;
        this.taskId = taskId;
        this.authorId = authorId;
    }

    //get from DB
    public Comment(Long id, String text, Long taskId, Long authorId,  LocalDateTime lastUpdate) {
        this.id = id;
        this.taskId = taskId;
        this.authorId = authorId;
        this.text = text;
        this.lastUpdate = lastUpdate;
    }*/

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

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Task getTask() { return task; }

    public void setTask(Task task) { this.task = task; }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }
}
