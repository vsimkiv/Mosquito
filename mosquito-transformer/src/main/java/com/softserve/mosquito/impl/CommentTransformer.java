package com.softserve.mosquito.impl;

import com.softserve.mosquito.api.Transformer;
import com.softserve.mosquito.dtos.CommentCreateDto;
import com.softserve.mosquito.entities.Comment;
import com.softserve.mosquito.entities.Task;
import com.softserve.mosquito.entities.User;

public class CommentTransformer {

    private CommentTransformer() {
        throw new IllegalStateException("Utility class");
    }

    public static class CommentCreate implements Transformer<Comment, CommentCreateDto> {

        @Override
        public Comment toEntity(CommentCreateDto commentCreateDto) {
            return new Comment(
                    commentCreateDto.getId(),
                    commentCreateDto.getText(),
                    new Task(commentCreateDto.getTaskId()),
                    new User(commentCreateDto.getAuthorId()));
        }

        @Override
        public CommentCreateDto toDTO(Comment comment) {
            return new CommentCreateDto(
                    comment.getId(),
                    comment.getText(),
                    comment.getTask().getId(),
                    comment.getAuthor().getId());
        }
    }

}