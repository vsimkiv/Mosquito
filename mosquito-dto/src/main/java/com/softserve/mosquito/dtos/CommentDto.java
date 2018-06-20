package com.softserve.mosquito.dtos;

import java.time.LocalDateTime;

/*
 * DTO for creating comments
 */
public class CommentDto {
    private Long id;
    private String text;
    private Long authorId;
    private Long taskId;
    private LocalDateTime lastUpdate;

    public CommentDto() {
    }

    public CommentDto(Long id, String text, Long authorId, Long taskId, LocalDateTime lastUpdate) {
        this.id = id;
        this.text = text;
        this.authorId = authorId;
        this.taskId = taskId;
        this.lastUpdate = lastUpdate;
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

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public String toString() {
        return "CommentDto{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", authorId=" + authorId +
                ", taskId=" + taskId +
                ", lastUpdate=" + lastUpdate +
                '}';
    }
}
