package com.softserve.mosquito.controllers;


import com.softserve.mosquito.dtos.PriorityCreateDto;
import com.softserve.mosquito.dtos.PriorityDto;
import com.softserve.mosquito.services.api.PriorityService;
import com.softserve.mosquito.services.impl.PriorityServiceImpl;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import java.util.List;

@Path("/priorities")
public class PriorityController {
    private PriorityService priorityService = new PriorityServiceImpl();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createPriority(PriorityCreateDto priorityCreateDto){
        PriorityDto createdPriorityDto = priorityService.createPriority(priorityCreateDto);

        if(createdPriorityDto == null){
            return Response.status(Status.BAD_REQUEST).build();
        }

        return Response.status(Status.OK).entity(createdPriorityDto).build();
    }

    @GET
    @Path("/{priority_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPriorityById(@PathParam("priority_id") Byte id){
        PriorityDto priority = priorityService.getPriorityById(Long.valueOf(id));

        if(priority == null) {
            return Response.status(Status.NOT_FOUND).build();
        }else {
            return Response.status(Status.OK).entity(priority).build();
        }
    }


    @PUT
    @Path("/{priority_id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updatePriority(@PathParam("priority_id") Byte id, PriorityCreateDto priorityCreateDto){
        PriorityDto priorityDto = new PriorityDto(id, priorityCreateDto.getTitle());
        PriorityDto updatetedPriotityDto = priorityService.updatePriority(priorityDto);

        if(updatetedPriotityDto == null){
            return Response.status(Status.BAD_REQUEST).build();
        }
        return Response.status(Status.OK).entity(updatetedPriotityDto).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deletePriority(@PathParam("id") Byte id) {
        priorityService.removePriority(Long.valueOf(id));

        return Response.status(Status.OK).build();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAllPriority(){
        List<PriorityDto>priorities = priorityService.getAllPriorities();

        if(priorities == null || priorities.isEmpty()) {
            return Response.status(Status.NOT_FOUND).build();
        }

        return Response.status(Status.OK).entity(priorities).build();
    }
}
