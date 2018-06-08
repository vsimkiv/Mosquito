package com.softserve.mosquito.controllers;

import com.softserve.mosquito.dtos.CommentCreateDto;
import com.softserve.mosquito.entities.Comment;
import com.softserve.mosquito.services.api.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {

    private CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping(path = "/{task_id}/comments")
    @ResponseStatus(HttpStatus.OK)
    public CommentCreateDto createComment(@PathVariable("task_id") Long taskId,
                                          @RequestBody CommentCreateDto commentDto) {
        return commentService.save(commentDto);
    }

    @GetMapping(path = "/{comment_id}/comments")
    @ResponseStatus(HttpStatus.OK)
    public CommentCreateDto getCommentsById(@PathVariable("comment_id") Long taskId) {

        return commentService.getCommentById(taskId);
    }

    @PutMapping(path = "/{comment_id}")
    @ResponseStatus(HttpStatus.OK)
    public CommentCreateDto updateComment(@PathVariable("comment_id") Long commentId,
                                          @RequestBody CommentCreateDto comment) {
        comment.setId(commentId);
        return commentService.update(comment);
    }

    @DeleteMapping(path = "/{commentId}")
    public HttpStatus deleteComments(@PathVariable("commentId") Long commentId) {
        commentService.delete(commentId);
        return HttpStatus.OK;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Comment> getAll() {
        return commentService.getAllComments();
    }
}