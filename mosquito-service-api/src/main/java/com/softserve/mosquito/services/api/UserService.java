package com.softserve.mosquito.services.api;

import com.softserve.mosquito.dtos.UserDto;

import java.util.List;

public interface UserService {

    UserDto createUser(UserDto user);

    UserDto updateUser(UserDto user);

    void removeUser(Long id);

    UserDto getUserById(Long id);

    List<UserDto> getAllUsers();

    UserDto getUserByEmail(String email);

    List<UserDto> getUsersBySpecialization(Long specializationId);
}
