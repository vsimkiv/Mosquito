package com.softserve.mosquito.services.impl;


import com.softserve.mosquito.dtos.UserDto;
import com.softserve.mosquito.entities.Specialization;
import com.softserve.mosquito.entities.User;
import com.softserve.mosquito.repo.api.UserRepo;
import com.softserve.mosquito.services.api.UserService;
import com.softserve.mosquito.services.mail.MailSender;
import com.softserve.mosquito.transformer.UserTransformer;
import org.hashids.Hashids;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepo userRepo;
    private MailSender mailSender;
    private SimpMessagingTemplate template;

    @Autowired
    public UserServiceImpl(SimpMessagingTemplate template, UserRepo userRepo, MailSender mailSender) {
        this.template = template;
        this.userRepo = userRepo;
        this.mailSender = mailSender;
    }

    @Override
    public UserDto save(UserDto user) {
        User activateUser = userRepo.create(UserTransformer.toEntity(user));
        Hashids hashids = new Hashids();
        hashids.encode(activateUser.getId());
        String urlForActivate = "http://localhost:8080/activate/" + hashids.encode(activateUser.getId());
        sendMessageForActivation(user, urlForActivate);
        return user;
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
        List<User> users = userRepo.readAll();
        List<UserDto> userDtos = new ArrayList<>();
        for (User user : users) {
            for (Specialization item : user.getSpecializations()) {
                if (item.getId().equals(specializationId))
                    userDtos.add(UserTransformer.toDTO(user));
            }
        }
        return userDtos;
    }

    @Override
    public void sendPushMessage(String message, Long userId) {
        template.convertAndSendToUser(String.valueOf(userId), "/queue/reply", message);
    }

    @Override
    public void activateUser(String key) {
        long id = new Hashids().decode(key)[0];
        User user = userRepo.read(id);
        user.setConfirmed(true);
        userRepo.update(user);
    }

    private void sendMessageForActivation(UserDto userDto, String message) {
        mailSender.sendMessage(userDto, "Activate your account " + message, "Mosquito Activate Account");
    }

}


