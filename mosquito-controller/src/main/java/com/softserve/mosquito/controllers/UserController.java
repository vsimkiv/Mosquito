package com.softserve.mosquito.controllers;

import com.softserve.mosquito.dtos.UserDto;
import com.softserve.mosquito.services.api.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@Api(value = "User controller", description = "Controller for doing CRUD operation with user")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @ApiOperation(value = "Add new user", response = UserDto.class)
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        UserDto createdUser = userService.save(userDto);
        if (createdUser == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().body(createdUser);
    }

    @GetMapping
    @ApiOperation(value = "Get all users in system", response = UserDto.class, responseContainer = "List")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok().body(userService.getAll());
    }

    @GetMapping("/{userId}")
    @ApiOperation(value = "Get concrete user", response = UserDto.class)
    public ResponseEntity<UserDto> getUserById(@PathVariable("userId") long id) {
        return ResponseEntity.ok().body(userService.getById(id));
    }

    @PutMapping("/{userId}")
    @ApiOperation(value = "Update user in system", response = UserDto.class)
    public ResponseEntity<UserDto> updateUser(@PathVariable("userId") long id, @RequestBody UserDto userDto) {
        userDto.setId(id);
        userDto = userService.update(userDto);
        if (userDto == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().body(userDto);
    }

    @DeleteMapping("/{userId}")
    @ApiOperation(value = "Delete user")
    public ResponseEntity<UserDto> deleteUserById(@PathVariable("userId") long id) {
        userService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/specializations/{specializationId}")
    @ApiOperation(value = "Get all users with concrete specialization", response = UserDto.class, responseContainer = "List")
    public ResponseEntity<List<UserDto>> getUsersBySpecializationId(@PathVariable("specializationId") long specializationId) {
        return ResponseEntity.ok().body(userService.getBySpecializationId(specializationId));
    }
}
