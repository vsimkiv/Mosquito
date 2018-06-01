package com.softserve.mosquito.services.api;

import com.softserve.mosquito.entities.User;

import java.util.List;

public interface UserService {

    User createUser(User user);

    User getUserById(Long id);

    User updateUser(User user);

    void removeUser(Long id);

    List<User> getAllUsers();

    User getUserByEmail(String email);

    List<User> getUsersBySpecialization(Long specializationId);
}
