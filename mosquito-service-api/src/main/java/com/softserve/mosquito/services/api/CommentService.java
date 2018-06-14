package com.softserve.mosquito.services.api;

import com.softserve.mosquito.dtos.CommentDto;

import java.util.List;


public interface CommentService {

    CommentDto save(CommentDto commentDto);

    CommentDto getById(Long id);

    CommentDto update(CommentDto commentDto);

    void delete(Long id);

    List<CommentDto> getByTaskId(Long taskId);

}
