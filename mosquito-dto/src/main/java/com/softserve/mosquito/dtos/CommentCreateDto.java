package com.softserve.mosquito.dtos;

/*
 * DTO for creating comments
 */
public class CommentCreateDto {
    private Long id;
    private String text;
    private Long authorId;
    private Long taskId;

    public CommentCreateDto() {
    }

    public CommentCreateDto(Long id, String text, Long taskId, Long authorId) {
        this.id = id;
        this.text = text;
        this.authorId = authorId;
        this.taskId = taskId;

    }

    public CommentCreateDto(String text, Long taskId, Long authorId) {
        this.text = text;
        this.taskId = taskId;
        this.authorId = authorId;
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

    @Override
    public String toString() {
        return "CommentCreateDto{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", authorId=" + authorId +
                ", taskId=" + taskId +
                '}';
    }
}
