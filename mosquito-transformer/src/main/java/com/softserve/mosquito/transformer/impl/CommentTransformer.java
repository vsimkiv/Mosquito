package com.softserve.mosquito.transformer.impl;

import com.softserve.mosquito.dtos.CommentCreateDto;
import com.softserve.mosquito.entities.Comment;
import com.softserve.mosquito.entities.Task;
import com.softserve.mosquito.entities.User;

public class CommentTransformer {

    private CommentTransformer() {
        throw new IllegalStateException("Utility class");
    }


    public static Comment toEntity(CommentCreateDto commentCreateDto) {
        return new Comment(
                commentCreateDto.getId(),
                commentCreateDto.getText(),
                new Task(commentCreateDto.getTaskId()),
                new User(commentCreateDto.getAuthorId()));
    }

    public static CommentCreateDto toDTO(Comment comment) {
        return new CommentCreateDto(
                comment.getId(),
                comment.getText(),
                comment.getTask().getId(),
                comment.getAuthor().getId());
    }


}