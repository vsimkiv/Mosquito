package com.softserve.mosquito.controllers;

import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.types.User;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.softserve.mosquito.dtos.UserDto;
import com.softserve.mosquito.services.api.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/social/facebook")
@Api(value = "User FB controller", description = "Controller for authorization using facebook")
public class UserFBControler {
    private static final String MY_APP_ACCESS_TOKEN="178289142884993|v56UAfXm1bqnU3Zro-KbwHAFYqw";

    private UserService userService;

    @Autowired
    public UserFBControler(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/signin")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<UserDto> getUserByFb() {

        FacebookClient facebookClient = new DefaultFacebookClient(MY_APP_ACCESS_TOKEN, Version.VERSION_2_5);

        User userFB = facebookClient.fetchObject("me", User.class,
                Parameter.with("fields",
                        "id, name, email, first_name, last_name"));


        return ResponseEntity.ok().body(userService.getByEmail(userFB.getEmail()));
    }
}