package com.softserve.mosquito.controllers;


import com.softserve.mosquito.dtos.LogWorkCreateDto;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

@Path("/task-log-works")
public class LogWorkController {

    @GET
    @Path("/{task_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLogWorksForTask(@PathParam("task_id") Long taskId) {
        //TODO: Change. For testing.
        return Response.status(Status.OK).build();
    }

    @POST
    @Path("/{task_id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createLogWork(@PathParam("task_id") Long taskId, LogWorkCreateDto logWorkCreateDto) {
        //TODO: Change. For testing.
        return Response.status(Status.OK).build();
    }

    @PUT
    @Path("/{task_id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateLogWorksForTask(@PathParam("task_id") Long taskId, LogWorkCreateDto logWorkCreateDto) {
        //TODO: Change. For testing.
        return Response.status(Status.OK).build();
    }

    @DELETE
    @Path("/{task_id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteLogWorksForTask(@PathParam("task_id") Long taskId) {
        //TODO: Change. For testing.
        return Response.status(Status.OK).build();
    }
}
