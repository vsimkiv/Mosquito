package com.softserve.mosquito.controllers;

import com.softserve.mosquito.dtos.CommentCreateDto;
import com.softserve.mosquito.entities.Comment;
import com.softserve.mosquito.impl.CommentTransformer;
import com.softserve.mosquito.services.api.CommentService;
import com.softserve.mosquito.services.impl.CommentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/comment")
public class CommentController {

    private CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    
    @Path("/{task_id}/comments")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createComment(CommentCreateDto commentDto) {
        Comment comment = new CommentTransformer.CommentCreate().toEntity(commentDto);
        return Response
                .ok()
                .entity(commentService.save(comment))
                .build();
    }

    @GET
    @Path("/{task_id}/comments")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getCommentsByTaskId(@PathParam("task_id") Long taskId) {
        return Response
                .ok()
                .entity(service.getCommentById(taskId))
                .build();
    }

    @PUT
    @Path("/{comment_id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON})
    public Response updateComment(@PathParam("comment_id")Long commentId, Comment comment) {
        comment.setId(commentId);
        return Response
                .ok()
                .entity(service.update(comment))
                .build();
    }

    @DELETE
    @Path("/{commentId}")
    public Response deleteComments(@PathParam("commentId") Long commentId) {
        service.delete(commentId);
        return Response
                .ok()
                .build();

    }
}