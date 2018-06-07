package com.softserve.mosquito.services.impl;

import com.softserve.mosquito.api.Transformer;
import com.softserve.mosquito.dtos.CommentCreateDto;
import com.softserve.mosquito.entities.Comment;
import com.softserve.mosquito.impl.CommentTransformer;
import com.softserve.mosquito.repo.api.CommentRepo;
import com.softserve.mosquito.services.api.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepo repo;
    private Transformer<Comment, CommentCreateDto> transformer = new CommentTransformer.CommentCreate();

    @Autowired
    public CommentServiceImpl(CommentRepo repo) {
        this.repo = repo;
    }

    @Override
    @Transactional
    public CommentCreateDto save(CommentCreateDto commentCreateDto) {
        Comment comment = repo.create(transformer.toEntity(commentCreateDto));
        if (comment == null)
            return null;

        return transformer.toDTO(comment);
    }

    @Override
    @Transactional
    public CommentCreateDto getCommentById(Long id) {
        Comment comment = repo.read(id);

        if (comment == null)
            return null;
        System.out.println(transformer.toDTO(comment).toString());
        return transformer.toDTO(comment);
    }

    @Override
    @Transactional
    public CommentCreateDto update(CommentCreateDto commentCreateDto) {
        Comment comment = repo.update(transformer.toEntity(commentCreateDto));
        System.out.println(comment + " Service 2");
        if (comment == null)
            return null;

        return transformer.toDTO(comment);
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
}
