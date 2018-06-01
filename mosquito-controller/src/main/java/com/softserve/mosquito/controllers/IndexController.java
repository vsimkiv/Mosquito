package com.softserve.mosquito.controllers;

import com.softserve.mosquito.dtos.UserLoginDto;
import com.softserve.mosquito.dtos.UserRegistrationDto;
import com.softserve.mosquito.entities.User;
import com.softserve.mosquito.services.impl.UserServiceImpl;
import com.softserve.mosquito.validators.UserValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
public class IndexController {
    private com.softserve.mosquito.services.api.UserService userService = new UserServiceImpl();
    private UserValidator validation = new UserValidator();

    @GET
    @Produces(MediaType.TEXT_HTML)
    public String testIndex() {
        return "Hello Mosquito <br>" +
                "<a href = \"/users\">Get users </a>";
    }
    
    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(@Valid UserLoginDto userLoginDto, @Context HttpServletRequest request) {
       if(validation.isValidCredentials(userLoginDto)) {
    	   // Start session with authorized user
           //TODO: use UserDto instead of User as a return type
    	   User user = userService.getUserByEmail(userLoginDto.getEmail());
    	   HttpSession session = request.getSession();
    	   session.setAttribute("user_id", user.getId());
    	   
    	   return Response.status(Response.Status.OK).entity(user).build();
       }else {
    	   return Response.status(Response.Status.UNAUTHORIZED).build();
       }
    }


    @GET
    @Path("/logout")
    public Response logout(@Context HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        return Response.status(Response.Status.OK).build();
    }

    @POST
    @Path("/registration")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response registration(UserRegistrationDto user){

        if (validation.isRegistrationValid(user))
            return Response.ok().entity(user).build();

        return  Response.status(Response.Status.FORBIDDEN).entity(user).build();
    }
    
}
