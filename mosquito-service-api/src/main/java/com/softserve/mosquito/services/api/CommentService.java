package com.softserve.mosquito.services.api;

import com.softserve.mosquito.dtos.CommentCreateDto;
import com.softserve.mosquito.entities.Comment;

import java.util.List;


public interface CommentService {

    CommentCreateDto save(CommentCreateDto commentCreateDto);

    CommentCreateDto getCommentById(Long id);

    CommentCreateDto update(CommentCreateDto commentCreateDto);

    void delete(Long id);

    List<Comment> getAllComments();

    List<CommentCreateDto> getCommentByTask(Long taskId);

}
