package com.softserve.mosquito.services.impl;


import com.softserve.mosquito.api.Transformer;
import com.softserve.mosquito.entities.Specialization;
import com.softserve.mosquito.entities.User;
import com.softserve.mosquito.impl.SpecializationTransformer;
import com.softserve.mosquito.repo.api.SpecializationRepo;
import com.softserve.mosquito.repo.api.UserRepo;
import com.softserve.mosquito.repo.impl.SpecializationRepoImpl;
import com.softserve.mosquito.repo.impl.UserRepoImpl;
import com.softserve.mosquito.services.api.SpecializationService;
import com.softserve.mosquito.services.api.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {

    private UserRepo userRepo = new UserRepoImpl();

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

    @Override
    public List<User> getUsersBySpecialization(Long specializationId) {
        SpecializationRepo repo = new SpecializationRepoImpl();

        List<User> users = userRepo.readAll();
        users.removeIf(user -> !user.getSpecializations().contains(repo.read(specializationId)));

        return users;
    }
}
