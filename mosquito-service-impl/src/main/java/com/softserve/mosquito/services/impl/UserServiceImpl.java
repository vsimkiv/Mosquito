package com.softserve.mosquito.services.impl;


import com.softserve.mosquito.dtos.UserDto;
import com.softserve.mosquito.entities.Specialization;
import com.softserve.mosquito.entities.User;
import com.softserve.mosquito.repo.api.UserRepo;
import com.softserve.mosquito.services.api.UserService;
import com.softserve.mosquito.transformer.UserTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepo userRepo;

    @Autowired
    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDto save(UserDto user) {
        return UserTransformer.toDTO(userRepo.create(UserTransformer.toEntity(user)));
    }

    @Override
    public UserDto update(UserDto user) {
        return UserTransformer.toDTO(userRepo.update(UserTransformer.toEntity(user)));
    }

    @Override
    public void delete(Long id) {
        userRepo.delete(id);
    }

    @Override
    public List<UserDto> getAll() {
        return UserTransformer.toDTO(userRepo.getAll());
    }

    @Override
    public UserDto getById(Long id) {
        return UserTransformer.toDTO(userRepo.read(id));
    }


    public UserDto getByEmail(String email) {
        User result = userRepo.getAll().stream()
                .filter(user -> user.getEmail().equals(email)).findFirst().orElse(null);
        return UserTransformer.toDTO(result);
    }

    @Override
    public List<UserDto> getBySpecializationId(Long specializationId) {
        List<User> users = userRepo.getAll();
        List<UserDto> userDtos = new ArrayList<>();

        for (User user : users) {
            for (Specialization specialization : user.getSpecializations()) {
                if (specialization.getId().equals(specializationId))
                    userDtos.add(UserTransformer.toDTO(user));
            }
        }

        return userDtos;
    }
}
