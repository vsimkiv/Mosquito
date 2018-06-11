package com.softserve.mosquito.services.impl;

import com.softserve.mosquito.dtos.CommentCreateDto;
import com.softserve.mosquito.entities.Comment;
import com.softserve.mosquito.repo.api.CommentRepo;
import com.softserve.mosquito.services.api.CommentService;
import com.softserve.mosquito.transformer.impl.CommentTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepo repo;

    @Autowired
    public CommentServiceImpl(CommentRepo repo) {
        this.repo = repo;
    }

    @Override
    @Transactional
    public CommentCreateDto save(CommentCreateDto commentCreateDto) {
        Comment comment = repo.create(CommentTransformer.toEntity(commentCreateDto));
        if (comment == null)
            return null;

        return CommentTransformer.toDTO(comment);
    }

    @Override
    @Transactional
    public CommentCreateDto getCommentById(Long id) {
        Comment comment = repo.read(id);

        if (comment == null)
            return null;
        return CommentTransformer.toDTO(comment);
    }

    @Override
    @Transactional
    public CommentCreateDto update(CommentCreateDto commentCreateDto) {
        Comment comment = repo.update(CommentTransformer.toEntity(commentCreateDto));
        if (comment == null)
            return null;

        return CommentTransformer.toDTO(comment);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        repo.delete(id);
    }

    @Override
    @Transactional
    public List<Comment> getAllComments() {
        return repo.readAll();
    }

    @Override
    @Transactional
    public List<CommentCreateDto> getCommentByTask(Long taskId) {
        List<CommentCreateDto> dtos = new ArrayList<>();
        List<Comment> comments = getAllComments();
        for (Comment comment : comments)
            if (comment.getTask().getId().equals(taskId))
                dtos.add(CommentTransformer.toDTO(comment));
        return dtos;
    }
}
