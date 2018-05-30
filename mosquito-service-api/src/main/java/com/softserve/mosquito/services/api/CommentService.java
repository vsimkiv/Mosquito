package com.softserve.mosquito.services.api;

import com.softserve.mosquito.entities.Comment;

public interface CommentService {

    Comment save(Comment comment);

    Comment getComment(Long id);

    Comment update(Comment comment);

    void delete(Long id);

}
