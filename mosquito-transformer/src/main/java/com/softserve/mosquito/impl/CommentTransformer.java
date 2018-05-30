package com.softserve.mosquito.impl;

import com.softserve.mosquito.api.Transformer;
import com.softserve.mosquito.dtos.CommentCreateDto;
import com.softserve.mosquito.entities.Comment;

public class CommentTransformer {

    public static class CommentCreate implements Transformer<Comment,CommentCreateDto>{

        @Override
        public Comment toEntity(CommentCreateDto commentCreateDto) {
            return new Comment(
                    commentCreateDto.getText(),
                    commentCreateDto.getTaskId(),
                    commentCreateDto.getAuthorId());
        }

        @Override
        public CommentCreateDto toDTO(Comment comment) {
            return new CommentCreateDto(
                    comment.getText(),
                    comment.getTaskId(),
                    comment.getAuthorId());
        }
    }

}
