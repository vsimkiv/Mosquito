package com.softserve.mosquito.dtos;

/*
 * DTO for creating comments
 */
public class CommentCreateDto {
    private String text;
    private Long taskId;

    public CommentCreateDto() {
    }

    public CommentCreateDto(String text, Long taskId) {
        this.text = text;
        this.taskId = taskId;
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
}
