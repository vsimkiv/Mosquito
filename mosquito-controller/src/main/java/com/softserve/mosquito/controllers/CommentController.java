package com.softserve.mosquito.controllers;

import com.softserve.mosquito.dtos.CommentCreateDto;
import com.softserve.mosquito.services.api.CommentService;
import com.softserve.mosquito.services.impl.CommentServiceImpl;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

@Path("/")
public class CommentController {

    private CommentService service = new CommentServiceImpl();

    @POST
    @Path("/tasks/{task_id}/comments")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createComment(@PathParam("task_id") Long taskId, CommentCreateDto commentDto) {
        //TODO: For testing. Use CommentService.create(CommentCreateDto)
        return Response.ok().entity(service.save(null)).build();
    }

    @GET
    @Path("tasks/{task_id}/comments")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getCommentsByTaskId(@PathParam("task_id") Long taskId) {
        //TODO: For testing. Use CommentService.getCommentsByTaskId(id)
        return Response.ok().entity(service.getComment(taskId)).build();
    }

//    @GET
//    @Path("/comments/{commentId}")
//    @Produces({MediaType.APPLICATION_JSON})
//    public Response getCommentByCommentId(@PathParam("commentId") Long commentId) {
//        //TODO: For testing. Use CommentService.getCommentByCommentId(id)
//        return Response.status(Status.OK).build();
//    }

    @PUT
    @Path("/comments/{comment_id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON})
    public Response updateComment(@PathParam("comment_id") Long commentId, CommentCreateDto commentUpdateDto) {
        //TODO: For testing. Use CommentService.updateComment(commentUpdateDto)
        return Response.status(Status.OK).build();
    }

    @DELETE
    @Path("/comments/{commentId}")
    public Response deleteComments(@PathParam("commentId") Long commentId) {
        //TODO: For testing. Use CommentService.deleteComment(id)
        return Response.status(Status.OK).build();
    }
}
