package com.softserve.mosquito.controllers;

import com.softserve.mosquito.dtos.TaskCreateDto;
import com.softserve.mosquito.repo.impl.TaskRepoImpl;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;


@Path("/tasks")
public class TaskController {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createSubTaskOrProject(TaskCreateDto taskCreateDto) {
        //TODO: Change. For testing.
        return Response.status(Status.OK).build();
    }

    @GET
    @Path("/workers")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getWorkerTasks(@QueryParam("worker_id") Long workerId) {
        //TODO: Change. For testing.
        return Response.status(Status.OK).build();
    }

    @GET
    @Path("/owners")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOwnerTasks(@QueryParam("owner_id") Long ownerId) {
        //TODO: Change. For testing.
        return Response.status(Status.OK).build();
    }

    @GET
    @Path("/{task_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTaskById(@PathParam("task_id") Long taskId) {
        //TODO: Change. For testing.
        return Response.status(Status.OK).build();
    }

    @PUT
    @Path("/{task_id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateTask(TaskCreateDto taskCreateDto) {
        //TODO: Change. For testing.
        return Response.status(Status.OK).build();

    }

    @GET
    @Path("/parents")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSubTaskOrProject(@QueryParam("parent_id") Long parentId) {
        //TODO: Change. For testing.
        return Response.status(Status.OK).build();
    }

    @DELETE
    @Path("/{task_id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteTask(@PathParam("task_id") Long taskId) {
        //TODO: Change. For testing.
        return Response.status(Status.OK).build();
    }

    @GET
    @Path("/statuses")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTaskByStatus(@QueryParam("task_status") Long statusId) {
        //TODO: Change. For testing.
        return Response.status(Status.OK).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response testAllTasks() {
        //TODO: Change. For testing.
        return Response.status(Status.OK).entity(new TaskRepoImpl().readAll()).build();
    }
}
