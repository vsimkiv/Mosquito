package com.softserve.mosquito.controllers;

import com.softserve.mosquito.dtos.UserDto;
import com.softserve.mosquito.services.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        UserDto createdUser = userService.createUser(userDto);
        if (createdUser == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().body(createdUser);
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok().body(userService.getAllUsers());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("userId") long id) {
        return ResponseEntity.ok().body(userService.getUserById(id));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("userId") long id, @RequestBody UserDto userDto) {
        userDto.setId(id);
        userDto = userService.updateUser(userDto);
        if (userDto == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().body(userDto);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<UserDto> deleteUserById(@PathVariable("userId") long id) {
        userService.removeUser(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/specializations/{specializationId}")
    public ResponseEntity<List<UserDto>> getUsersBySpecializationId(@PathVariable("specializationId") long specializationId) {
        return ResponseEntity.ok().body(userService.getUsersBySpecialization(specializationId));
    }

}
