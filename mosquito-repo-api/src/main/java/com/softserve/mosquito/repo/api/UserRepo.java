package com.softserve.mosquito.repo.api;

import com.softserve.mosquito.entities.User;

import java.util.List;

public interface UserRepo extends GenericCRUD<User> {

    List<User> getAll();
}
