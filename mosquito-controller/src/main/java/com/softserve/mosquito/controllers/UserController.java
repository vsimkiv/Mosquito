package com.softserve.mosquito.controllers;

import com.softserve.mosquito.dtos.UserRegistrationDto;
import com.softserve.mosquito.dtos.UserUpdateDto;
import com.softserve.mosquito.entities.User;
import com.softserve.mosquito.services.impl.UserServiceImpl;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

@Path("/users")
public class UserController {

    private com.softserve.mosquito.services.api.UserService userService = new UserServiceImpl();
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
    public Response createUser(UserRegistrationDto userRegistrationDto) {
        //TODO: Change. For testing.
        return Response.status(Status.OK).build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
    public Response getAllUsers() {
        return Response.status(Status.OK).entity(userService.getAllUsers()).build();
	}
	
	@GET
	@Path("/{userId}")
	@Produces(MediaType.APPLICATION_JSON)
    public Response getUserById(@PathParam("userId") long userId) {
        return Response.status(Status.OK).entity(userService.getUserById(userId)).build();
	}
	
	@PUT
	@Path("/{userId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
    public Response updateUser(@PathParam("userId") long userId, UserUpdateDto userUpdateDto) {
		User user = new User();
		//TODO: Modify userService.update(User) => userService.update(UserUpdateDtp)
        return Response.status(Status.OK).entity(userUpdateDto).build();
	}
	
	@DELETE
	@Path("/{userId}")
	public Response deleteUserById(@PathParam("userId") long userId) {
        userService.removeUser(userService.getUserById(userId));
        return Response.ok().build();
	}
	
	/*
	 * Return all users with specific specialization
	 * @param: specializationId - path parameter: users/specializations/2 (2 - Project Manager)
	 */
	@GET
	@Path("/specializations/{specializationId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUsersBySpecializationId(@PathParam("specializationId") long specializationId) {
        //TODO: For testing. Create in UserServiceImpl method - getUsersBySpecialization(long specializationId)
		return Response.ok().entity("All users with Specialization " + specializationId).build();
	}
	
}
