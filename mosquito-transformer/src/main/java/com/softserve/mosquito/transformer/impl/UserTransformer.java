package com.softserve.mosquito.transformer.impl;

import com.softserve.mosquito.dtos.UserDto;
import com.softserve.mosquito.entities.User;
import com.softserve.mosquito.transformer.api.Transformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserTransformer implements Transformer<User, UserDto> {

    private SpecializationTransformer transformer;

    @Autowired
    public UserTransformer(SpecializationTransformer transformer) {
        this.transformer = transformer;
    }

    @Override
    public User toEntity(UserDto userDto) {
        return new User(userDto.getEmail(), userDto.getPassword(), userDto.getFirstName(),
                userDto.getLastName(), transformer.toEntity(userDto.getSpecializations()));
    }

    @Override
    public UserDto toDTO(User user) {
        return UserDto.newBuilder().id(user.getId()).email(user.getEmail()).password(user.getPassword())
                .firstName(user.getFirstName()).lastName(user.getLastName())
                .specializations(transformer.toDTO(user.getSpecializations())).build();
    }
}
