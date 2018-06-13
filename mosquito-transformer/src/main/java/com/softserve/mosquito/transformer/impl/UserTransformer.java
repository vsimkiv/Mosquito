package com.softserve.mosquito.transformer.impl;

import com.softserve.mosquito.dtos.UserDto;
import com.softserve.mosquito.entities.User;

import java.util.ArrayList;
import java.util.List;

public class UserTransformer {

    private static SpecializationTransformer specializationTransformer = new SpecializationTransformer();

    private UserTransformer() {
        throw new IllegalStateException("Utility class");
    }

    public static User toEntity(UserDto userDto) {
        if (userDto == null)
            return null;
        else
            return new User(userDto.getEmail(), userDto.getPassword(), userDto.getFirstName(),
                    userDto.getLastName(), specializationTransformer.toEntityList(userDto.getSpecializations()));
    }

    public static UserDto toDTO(User user) {
        if (user == null)
            return null;
        else
            return UserDto.newBuilder().id(user.getId()).email(user.getEmail()).password(user.getPassword())
                    .confirmPassword(user.getPassword()).firstName(user.getFirstName()).lastName(user.getLastName())
                    .specializations(specializationTransformer.toDTOList(user.getSpecializations())).build();
    }

    public static List<User> toEntity(List<UserDto> userDtos) {
        if (userDtos == null)
            return null;
        else {
            List<User> users = new ArrayList<>();
            for (UserDto item : userDtos)
                users.add(toEntity(item));
            return users;
        }
    }

    public static List<UserDto> toDTO(List<User> users) {
        if (users == null)
            return null;
        else {
            List<UserDto> userDtos = new ArrayList<>();
            for (User item : users)
                userDtos.add(toDTO(item));
            return userDtos;
        }
    }
}