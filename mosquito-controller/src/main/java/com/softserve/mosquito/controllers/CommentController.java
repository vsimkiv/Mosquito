package com.softserve.mosquito.controllers;

import com.softserve.mosquito.dtos.CommentDto;
import com.softserve.mosquito.services.api.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
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
    public CommentDto createComment(@PathVariable("task_id") Long taskId,
                                    @RequestBody CommentDto commentDto) {
        commentDto.setTaskId(taskId);
        return commentService.save(commentDto);
    }

    @GetMapping(path = "/{task_id}/comments")
    @ResponseStatus(HttpStatus.OK)
    public List<CommentDto> getCommentsByTaskId(@PathVariable("task_id") Long taskId) {
        if (commentService.getByTaskId(taskId).equals(Collections.emptyList()))
            throw new CommentNotFoundException("Comments for task with Id " + taskId + " not found!");
        return commentService.getByTaskId(taskId);
    }

    @PutMapping(path = "/{comment_id}")
    @ResponseStatus(HttpStatus.OK)
    public CommentDto updateComment(@PathVariable("comment_id") Long commentId,
                                    @RequestBody CommentDto comment) {
        comment.setId(commentId);
        return commentService.update(comment);
    }

    @DeleteMapping(path = "/{commentId}")
    public HttpStatus deleteComments(@PathVariable("commentId") Long commentId) {
        commentService.delete(commentId);
        return HttpStatus.OK;
    }
}