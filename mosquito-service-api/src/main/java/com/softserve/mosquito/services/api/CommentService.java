package com.softserve.mosquito.services.api;

import com.softserve.mosquito.dtos.CommentCreateDto;

import java.util.List;


public interface CommentService {

    CommentCreateDto save(CommentCreateDto commentCreateDto);

    CommentCreateDto getById(Long id);

    CommentCreateDto update(CommentCreateDto commentCreateDto);

    void delete(Long id);

    List<CommentCreateDto> getByTaskId(Long taskId);

}
