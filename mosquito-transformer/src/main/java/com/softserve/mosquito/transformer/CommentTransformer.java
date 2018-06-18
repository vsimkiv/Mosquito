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

    public static List<CommentDto> toDTOList(List<Comment> comments){
        List<CommentDto> commentDtos = new ArrayList<>();
        for (Comment comment : comments){
            commentDtos.add(new CommentDto(
                    comment.getId(),
                    comment.getText(),
                    comment.getTask().getId(),
                    comment.getAuthor().getId())
            );
        }
        return commentDtos;
    }
}