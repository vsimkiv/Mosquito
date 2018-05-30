package com.softserve.mosquito.services.impl;

import com.softserve.mosquito.entities.Comment;
import com.softserve.mosquito.repo.api.CommentRepo;
import com.softserve.mosquito.repo.impl.CommentRepoImpl;
import com.softserve.mosquito.services.api.CommentService;

public class CommentServiceImpl implements CommentService {

    private CommentRepo repo = new CommentRepoImpl();

    @Override
    public Comment save(Comment comment) {
        return repo.create(comment);
    }

    @Override
    public Comment getComment(Long id) {
        return repo.read(id);
    }

    @Override
    public Comment update(Comment comment) {
        return repo.update(comment);
    }

    @Override
    public void delete(Comment comment) {
        repo.delete(comment);
    }
}
