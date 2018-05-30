package com.softserve.mosquito.controllers;

import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.softserve.mosquito.dtos.SpecializationCreateDto;
import com.softserve.mosquito.dtos.SpecializationDto;
import com.softserve.mosquito.services.api.SpecializationService;
import com.softserve.mosquito.services.impl.SpecializationServiceImpl;

@Path("/specializations")
public class SpecializationController {
    private SpecializationService specializationService = new SpecializationServiceImpl();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createSpecialization(SpecializationCreateDto specializationCreateDto){
        SpecializationDto createdSpecialization = specializationService.createSpecialization(specializationCreateDto);

        if(createdSpecialization == null) {
            return Response.status(Status.BAD_REQUEST).build();
        }

        return Response.status(Status.CREATED).entity(createdSpecialization).build();
    }

    @GET
    @Path("/{specialization_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSpecializationById(@PathParam("specialization_id") Long specialization_id){
        SpecializationDto specialization = specializationService.getSpecializationById(specialization_id);

        if(specialization == null) {
            return Response.status(Status.NOT_FOUND).build();
        }

        return Response.status(Status.OK).entity(specialization).build();
    }

    @PUT
    @Path("/{specialization_id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateSpecialization(@PathParam("specialization_id") Byte specialization_id, SpecializationCreateDto specializationCreateDto){
        SpecializationDto specializationForUpdate = new SpecializationDto(specialization_id, specializationCreateDto.getTitle());
        SpecializationDto updatedSpacialization = specializationService.updateSpecialization(specializationForUpdate);

        if(updatedSpacialization == null){
            return Response.status(Status.BAD_REQUEST).build();
        }
        return Response.status(Status.OK).entity(updatedSpacialization).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllSpecializations(){
        List<SpecializationDto>specializations = specializationService.getAllSpecializations();

        if(specializations == null || specializations.isEmpty()) {
            return Response.status(Status.NOT_FOUND).build();
        }

        return Response.status(Status.OK).entity(specializations).build();
    }

    @DELETE
    @Path("/{specialization_id}")
    public Response removeSpecialization(@PathParam("specialization_id") Long specialization_id){
        specializationService.removeSpecialization(specialization_id);
        return Response.status(Status.OK).build();
    }

}

