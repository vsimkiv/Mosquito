package com.softserve.mosquito.dtos;

/*
 * DTO for creating comments
 */
public class CommentCreateDto {
    private String text;
    private Long taskId;
    private Long authorId;

    public CommentCreateDto() {
    }

    public CommentCreateDto(String text, Long taskId, Long authorId) {
        this.text = text;
        this.taskId = taskId;
        this.authorId = authorId;
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
}
