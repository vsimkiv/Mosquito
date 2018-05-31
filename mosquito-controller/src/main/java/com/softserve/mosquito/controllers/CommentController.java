package com.softserve.mosquito.controllers;

import com.softserve.mosquito.dtos.CommentCreateDto;
import com.softserve.mosquito.entities.Comment;
import com.softserve.mosquito.impl.CommentTransformer;
import com.softserve.mosquito.services.api.CommentService;
import com.softserve.mosquito.services.impl.CommentServiceImpl;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

@Path("/task")
public class CommentController {

    private CommentService service = new CommentServiceImpl();

    @POST
    @Path("/{task_id}/comments")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createComment(CommentCreateDto commentDto) {
        Comment comment = new CommentTransformer.CommentCreate().toEntity(commentDto);
        System.out.println(comment);
        return Response
                .ok()
                .entity(service.save(comment))
                .build();
    }

    @GET
    @Path("/{task_id}/comments")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getCommentsByTaskId(@PathParam("task_id") Long taskId) {
        return Response
                .ok()
                .entity(service.getComment(taskId))
                .build();
    }

    @PUT
    @Path("/{comment_id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON})
    public Response updateComment(@PathParam("comment_id")Long commentId, Comment comment) {
        comment.setId(commentId);
        System.out.println(comment);
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