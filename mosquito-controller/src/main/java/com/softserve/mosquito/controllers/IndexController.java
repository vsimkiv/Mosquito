package com.softserve.mosquito.controllers;

import com.softserve.mosquito.dtos.UserDto;
import com.softserve.mosquito.services.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/")
public class IndexController {
    private UserService userService;

    @Autowired
    public IndexController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/swagger")
    @ResponseStatus(HttpStatus.OK)
    public ModelAndView swagger(){
        return new ModelAndView("swagger-ui.html");
    }

    @GetMapping(path = "/")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<UserDto>> testIndexController() {
        return ResponseEntity.ok().body(userService.getAll());
    }


    @PostMapping(path = "/login")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<UserDto> login(@RequestBody UserDto userDto, HttpServletRequest request) {
        UserDto foundUser = userService.getByEmail(userDto.getEmail());
        if (foundUser != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user_id", foundUser.getId());
            return ResponseEntity.ok().body(foundUser);
        } else {
            throw new UserNotFoundException();
        }
    }

    @GetMapping(path = "/logout")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ModelAndView logout(HttpServletRequest request) {
        ModelAndView view = new ModelAndView("Logged out");
        request.getSession().invalidate();
        return view;
    }

    @PostMapping(path = "/registration")
    @ResponseStatus(HttpStatus.OK)
    public UserDto registration(@Valid @RequestBody UserDto user) {
        return user;
    }

    private class UserNotFoundException extends RuntimeException {
    }
}