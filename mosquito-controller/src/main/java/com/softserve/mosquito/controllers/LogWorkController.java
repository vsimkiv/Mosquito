package com.softserve.mosquito.controllers;


import com.softserve.mosquito.dtos.LogWorkDto;
import com.softserve.mosquito.services.api.LogWorkService;
import com.softserve.mosquito.services.impl.LogWorkServiceImpl;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

@Path("/log-works")
public class LogWorkController {
    private LogWorkService logWorkService = new LogWorkServiceImpl();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllLogWorks() {
        return Response.status(Status.OK).entity(logWorkService.getAllLogWork().toString()).build();
    }

    @GET
    @Path("/{estimation_Id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLogWorksForTask(@PathParam("estimation_Id") Long estimation_Id) {
     return Response.ok(logWorkService.getLogWorksByEstimation(estimation_Id).toString()).build();
    }

    @POST
    @Path("/{logWork_id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createLogWork(@PathParam("logWork_id") Long taskId, LogWorkDto logWorkDto) {
       return Response.status(Status.OK).build();
    }

    @PUT
    @Path("/{logWork_id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateLogWorksForTask(@PathParam("logWork_id") Long taskId, LogWorkDto logWorkDto) {
        return Response.status(Status.OK).build();
    }

    @DELETE
    @Path("/{logWork_id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteLogWorksForTask(@PathParam("logWork_id") Long logWorkId) {
        return Response.status(Status.OK).build();
    }
}
