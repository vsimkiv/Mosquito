package com.softserve.mosquito.services.validation;

import com.softserve.mosquito.dtos.UserDto;
import com.softserve.mosquito.entities.User;
import com.softserve.mosquito.services.api.UserService;
import com.softserve.mosquito.transformer.impl.UserTransformer;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserValidator {

    private UserService userService;
    private static final String SALT = "r4OSxKpY";

    @Autowired
    public UserValidator(UserService userService) {
        this.userService = userService;
    }

    public boolean isRegistrationValid(UserDto user) {

        User userFromDB = new UserTransformer().toEntity(userService.getUserByEmail(user.getEmail()));
        if (userFromDB == null && user.getConfirmPassword().equals(user.getPassword())) {
            String password = DigestUtils.md5Hex(user.getPassword().concat(SALT));
            userFromDB = new User(user.getEmail(),
                    user.getFirstName(),
                    user.getLastName(),
                    password);
            return userService.createUser(new UserTransformer().toDTO(userFromDB)) != null;
        }

        return false;
    }
}
