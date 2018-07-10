package com.softserve.mosquito.services.api;

import com.softserve.mosquito.dtos.UserDto;

import java.util.List;

public interface UserService {

    UserDto save(UserDto user);

    UserDto update(UserDto user);

    void delete(Long id);

    UserDto getById(Long id);

    List<UserDto> getAll();

    UserDto getByEmail(String email);

    List<UserDto> getBySpecializationId(Long specializationId);

    void activateUser(String key);

    void sendPushMessage(String message, Long userId);

    boolean isConfirmed(String email);
}
