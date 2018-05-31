package com.softserve.mosquito.impl;

import com.softserve.mosquito.api.Transformer;
import com.softserve.mosquito.dtos.UserLoginDto;
import com.softserve.mosquito.dtos.UserRegistrationDto;
import com.softserve.mosquito.entities.User;

public class UserTransformer {

    private UserTransformer() {
        throw new IllegalStateException("Utility class");
    }

    static class UserLogin implements Transformer<User,UserLoginDto>{

        @Override
        public User toEntity(UserLoginDto loginDto) {
            return new User(
                    loginDto.getEmail(),
                    loginDto.getPassword());
        }

        @Override
        public UserLoginDto toDTO(User user) {
            return new UserLoginDto(
                    user.getId(),
                    user.getEmail(),
                    user.getPassword());
        }
    }

    static class UserRegistration implements Transformer<User,UserRegistrationDto>{

        @Override
        public User toEntity(UserRegistrationDto registrationDto) {
            return new User(registrationDto.getEmail(),
                    registrationDto.getFirstName(),
                    registrationDto.getLastName(),
                    registrationDto.getPassword());
        }

        @Override
        public UserRegistrationDto toDTO(User user) {
            return new UserRegistrationDto(user.getEmail(),
                    user.getFirstName(),
                    user.getLastName(),
                    user.getPassword());
        }
    }

}
