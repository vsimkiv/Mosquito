package com.softserve.mosquito.services.impl;


import com.softserve.mosquito.dtos.UserDto;
import com.softserve.mosquito.entities.Specialization;
import com.softserve.mosquito.entities.User;
import com.softserve.mosquito.repo.api.UserRepo;
import com.softserve.mosquito.services.api.UserService;
import com.softserve.mosquito.transformer.impl.UserTransformer;
import org.hibernate.mapping.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepo userRepo;
    private UserTransformer transformer = new UserTransformer();

    @Autowired
    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDto createUser(UserDto user) {
        return transformer.toDTO(userRepo.create(transformer.toEntity(user)));
    }

    @Override
    public UserDto updateUser(UserDto user) {
        return transformer.toDTO(userRepo.update(transformer.toEntity(user)));
    }

    @Override
    public void removeUser(Long id) {
        userRepo.delete(id);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepo.readAll();
        List<UserDto> userDtos = new ArrayList<>();
        for (User user : users) {
            userDtos.add(transformer.toDTO(user));
        }

        return userDtos;
    }

    @Override
    public UserDto getUserById(Long id) {
        return transformer.toDTO(userRepo.read(id));
    }


    public UserDto getUserByEmail(String email) {
        User result = userRepo.readAll().stream()
                .filter(user -> user.getEmail().equals(email)).findFirst().orElse(null);
        return transformer.toDTO(result);
    }

    @Override
    public List<UserDto> getUsersBySpecialization(Long specializationId) {
        List<User> users = userRepo.readAll();
        List<UserDto> userDtos = new ArrayList<>();

        for (User user : users) {
            for (Specialization specialization : user.getSpecializations()) {
                if (specialization.getId().equals(specializationId))
                    userDtos.add(transformer.toDTO(user));
            }
        }

        return userDtos;
    }
}
