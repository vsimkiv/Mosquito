package com.softserve.mosquito.repo.api;

import com.softserve.mosquito.entities.User;

import java.util.List;

public interface UserRepo extends GenericCRUD<User> {

    List<User> readAll();

    User readByEmail(String email);

    List<User> readBySpecializationId(Long id);
}
