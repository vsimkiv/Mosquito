package com.softserve.mosquito.controllers;

import com.softserve.mosquito.dtos.StatusCreateDto;
import com.softserve.mosquito.dtos.StatusDto;
import com.softserve.mosquito.services.api.StatusService;
import com.softserve.mosquito.services.impl.StatusServiceImpl;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import java.util.List;

@Path("/statuses")
public class StatusController {
    private StatusService statusService = new StatusServiceImpl() ;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createStatus(StatusCreateDto statusCreateDto) {
        StatusDto statusDto = statusService.createStatus(statusCreateDto);

        if(statusDto == null){
            return Response.status(Status.BAD_REQUEST).build();
        }

        return Response.status(Status.OK).entity(statusDto).build();
    }

    @GET
    @Path("/{status_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStatusById(@PathParam("status_id") Long id){
        StatusDto statusDto = statusService.getStatusById(id);

        if(statusDto == null) {
            return Response.status(Status.NOT_FOUND).build();
        }

        return Response.status(Status.OK).entity(statusDto).build();
    }


    @PUT
    @Path("/{status_id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateStatus(@PathParam("status_id") Byte status_id, StatusCreateDto statusCreateDto) {
        StatusDto statusDto = new StatusDto(status_id, statusCreateDto.getTitle());
        StatusDto updatedStatusDto = statusService.updateStatus(statusDto);

        if(updatedStatusDto == null){
            return Response.status(Status.BAD_REQUEST).build();
        }

        return Response.status(Status.OK).entity(updatedStatusDto).build();
    }

    @DELETE
    @Path("/{status_id}")
    public Response deletePriority(@PathParam("status_id") Long id) {
        statusService.removeStatus(id);

        return Response.status(Status.OK).build();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAllStatuses(){
        List<StatusDto> statuses = statusService.getAllStatus();

        if(statuses == null || statuses.isEmpty()) {
            return Response.status(Status.NOT_FOUND).build();
        }

        return Response.status(Response.Status.OK).entity(statuses).build();
    }
}

