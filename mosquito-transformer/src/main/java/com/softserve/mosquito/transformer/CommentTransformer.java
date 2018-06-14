package com.softserve.mosquito.transformer;

import com.softserve.mosquito.dtos.CommentDto;
import com.softserve.mosquito.entities.Comment;
import com.softserve.mosquito.entities.Task;
import com.softserve.mosquito.entities.User;

public class CommentTransformer {

    private CommentTransformer() {
        throw new IllegalStateException("Utility class");
    }


    public static Comment toEntity(CommentDto commentDto) {
        return new Comment(
                commentDto.getId(),
                commentDto.getText(),
                new Task(commentDto.getTaskId()),
                new User(commentDto.getAuthorId()));
    }

    public static CommentDto toDTO(Comment comment) {
        return new CommentDto(
                comment.getId(),
                comment.getText(),
                comment.getTask().getId(),
                comment.getAuthor().getId());
    }


}