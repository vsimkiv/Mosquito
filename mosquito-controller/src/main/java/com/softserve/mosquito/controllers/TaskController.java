package com.softserve.mosquito.controllers;

import com.softserve.mosquito.dtos.TaskDto;
import com.softserve.mosquito.entities.Task;
import com.softserve.mosquito.services.api.TaskService;
import com.softserve.mosquito.services.impl.TaskServiceImpl;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;


@Path("/tasks")
public class TaskController {

    private TaskService taskService = new TaskServiceImpl();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createSubTaskOrProject(TaskDto taskCreateDto) {
        Task task = taskService.createTask(taskCreateDto);
        return Response.status(Status.CREATED).entity(task).build();
    }

    @GET
    @Path("/workers")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getWorkerTasks(@QueryParam("worker_id") Long workerId,
                                   @QueryParam("status_id") Byte statusId) {
        return Response.ok(taskService.getTasksByWorkerAndStatus(workerId, statusId)).build();
    }

    @GET
    @Path("/owners")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOwnerTasks(@QueryParam("owner_id") Long ownerId,
                                  @QueryParam("status_id") Byte statusId,
                                  @QueryParam("parent_id") Long parentId) {
        return Response.ok(taskService.getTasksByOwnerAndStatusAndParent(parentId, ownerId, statusId)).build();
    }

    @GET
    @Path("/{task_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTaskById(@PathParam("task_id") Long taskId) {
        return Response.ok(taskService.getTaskById(taskId)).build();
    }

    @PUT
    @Path("/{task_id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateTask(TaskDto taskCreateDto) {
        //TODO: Change. For testing.
        return Response.status(Status.OK).build();

    }

    @GET
    @Path("/parents")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSubTaskOrProject(@QueryParam("parent_id") Long parentId) {
        return Response.ok(taskService.getSubTasks(parentId)).build();
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
    public Response getTaskByStatus(@QueryParam("task_status") Byte statusId) {
        //TODO: Change. For testing.
        return Response.status(Status.OK).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllTasks() {
        return Response.ok(taskService.getAllTasks()).build();
    }
}
