package com.softserve.mosquito.transformer;

import com.softserve.mosquito.dtos.CommentDto;
import com.softserve.mosquito.entities.Comment;
import com.softserve.mosquito.entities.Task;
import com.softserve.mosquito.entities.User;

import java.util.ArrayList;
import java.util.List;

public class CommentTransformer {

    private CommentTransformer() {
        throw new IllegalStateException("Utility class");
    }

    public static Comment toEntity(CommentDto commentDto) {
        if (commentDto == null) {
            return null;
        } else
            return new Comment(
                    commentDto.getId(),
                    commentDto.getText(),
                    User.builder().id(commentDto.getAuthorId()).build(),
                    Task.builder().id(commentDto.getTaskId()).build(),
                    commentDto.getLastUpdate());
    }

    public static CommentDto toDTO(Comment comment) {
        if (comment == null) {
            return null;
        } else
            return new CommentDto(
                    comment.getId(),
                    comment.getText(),
                    comment.getAuthor().getId(),
                    comment.getTask().getId(),
                    comment.getLastUpdate());
    }

    public static List<CommentDto> toDTOList(List<Comment> comments) {
        List<CommentDto> commentDtos = new ArrayList<>();
        for (Comment comment : comments) {
            commentDtos.add(new CommentDto(
                    comment.getId(),
                    comment.getText(),
                    comment.getAuthor().getId(),
                    comment.getTask().getId(),
                    comment.getLastUpdate()
            ));
        }
        return commentDtos;
    }
}