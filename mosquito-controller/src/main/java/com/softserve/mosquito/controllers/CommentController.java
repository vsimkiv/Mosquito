package com.softserve.mosquito.controllers;

import com.softserve.mosquito.dtos.CommentCreateDto;
import com.softserve.mosquito.entities.Comment;
import com.softserve.mosquito.services.api.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
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
    public CommentCreateDto createComment(@PathVariable("task_id") Long taskId, CommentCreateDto commentDto, Model model) {
        model.addAttribute("task_id", taskId);
        model.addAttribute("CommentDto", commentDto);
        return commentService.save(commentDto);
    }

    @GetMapping(path = "/{task_id}/comments")
    @ResponseStatus(HttpStatus.OK)
    public CommentCreateDto getCommentsByTaskId(@PathVariable("task_id") Long taskId, Model model) {
        model.addAttribute("task_id", taskId);

        return commentService.getCommentById(taskId);
    }

    @PutMapping(path = "/{comment_id}")
    @ResponseStatus(HttpStatus.OK)
    public CommentCreateDto updateComment(@PathVariable("comment_id") Long commentId, CommentCreateDto comment, Model model) {
        model.addAttribute("CommentId", commentId);
        model.addAttribute("CommentForUpdate", comment);
        comment.setId(commentId);
        return commentService.update(comment);
    }

    @DeleteMapping(path = "/{commentId}")
    public HttpStatus deleteComments(@PathVariable("commentId") Long commentId, Model model) {
        model.addAttribute("CommentId", commentId);
        commentService.delete(commentId);
        return HttpStatus.OK;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Comment> getAll(){
        return commentService.getAllComments();
    }
}