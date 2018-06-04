package com.softserve.mosquito.services.impl;


import com.softserve.mosquito.entities.User;
import com.softserve.mosquito.repo.api.UserRepo;
import com.softserve.mosquito.repo.impl.UserRepoImpl;
import com.softserve.mosquito.services.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {

    private UserRepo userRepo;

    @Autowired
    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public User createUser(User user) {
        return userRepo.create(user);
    }

    @Override
    public User updateUser(User user) {
        return userRepo.update(user);
    }

    @Override
    public void removeUser(Long id) {
        userRepo.delete(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.readAll();
    }

    @Override
    public User getUserById(Long id) {
        return userRepo.read(id);
    }


    public User getUserByEmail(String email) {
        return ((UserRepoImpl) userRepo).readUserByEmail(email);
    }
}
