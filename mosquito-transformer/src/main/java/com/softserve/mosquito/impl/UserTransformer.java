package com.softserve.mosquito.impl;

import com.softserve.mosquito.api.Transformer;
import com.softserve.mosquito.dtos.UserDto;
import com.softserve.mosquito.entities.User;
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
        return UserDto.newBuilder().setId(user.getId()).setEmail(user.getEmail()).setPassword(user.getPassword())
                .setFirstName(user.getFirstName()).setLastName(user.getLastName())
                .setSpecializations(transformer.toDTO(user.getSpecializations())).build();
    }
}
