package com.softserve.mosquito.services.impl;


import com.softserve.mosquito.dtos.UserDto;
import com.softserve.mosquito.repo.api.UserRepo;
import com.softserve.mosquito.services.api.UserService;
import com.softserve.mosquito.transformer.UserTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepo userRepo;
    private SimpMessagingTemplate template;

    @Autowired
    public UserServiceImpl(SimpMessagingTemplate template, UserRepo userRepo) {
        this.template = template;
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
        return UserTransformer.toDTO(userRepo.readAll());
    }

    @Override
    public UserDto getById(Long id) {
        return UserTransformer.toDTO(userRepo.read(id));
    }


    public UserDto getByEmail(String email) {
        return UserTransformer.toDTO(userRepo.readByEmail(email));
    }

    @Override
    public List<UserDto> getBySpecializationId(Long specializationId) {
        return UserTransformer.toDTO(userRepo.readBySpecializationId(specializationId));
    }

    @Override
    public void sendPushMessage(Long userId) {
        template.convertAndSendToUser(String.valueOf(userId),"/queue/reply", "hello!");
    }
}
