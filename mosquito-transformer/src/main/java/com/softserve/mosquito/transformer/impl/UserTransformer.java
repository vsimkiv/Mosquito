package com.softserve.mosquito.transformer.impl;

import com.softserve.mosquito.dtos.UserDto;
import com.softserve.mosquito.entities.User;
import com.softserve.mosquito.transformer.api.Transformer;

public class UserTransformer implements Transformer<User, UserDto> {

    private SpecializationTransformer specializationTransformer = new SpecializationTransformer();

    public UserTransformer() {
    }

    @Override
    public User toEntity(UserDto userDto) {
        return new User(userDto.getEmail(), userDto.getPassword(), userDto.getFirstName(),
                userDto.getLastName(), specializationTransformer.toEntity(userDto.getSpecializations()));
    }

    @Override
    public UserDto toDTO(User user) {
        return UserDto.newBuilder().id(user.getId()).email(user.getEmail()).password(user.getPassword())
                .confirmPassword(user.getPassword()).firstName(user.getFirstName()).lastName(user.getLastName())
                .specializations(specializationTransformer.toDTO(user.getSpecializations())).build();
    }
}
