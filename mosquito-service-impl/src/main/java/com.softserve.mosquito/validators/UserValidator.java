package com.softserve.mosquito.validators;

import com.softserve.mosquito.dtos.UserLoginDto;
import com.softserve.mosquito.dtos.UserRegistrationDto;
import com.softserve.mosquito.entities.User;
import com.softserve.mosquito.services.api.UserService;
import com.softserve.mosquito.services.impl.UserServiceImpl;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidator {

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    private static final String EMAIL_REGEX =
            "^[_A-Za-z0-9-+]+(.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(.[A-Za-z0-9]+)*(.[A-Za-z]{2,}$)";

    private static final int MIN_LENGTH_FIRST_NAME = 2;
    private static final int MIN_LENGTH_LAST_NAME = 2;
    private static final int MIN_LENGTH_PASSWORD = 7;

    private String salt = "r4OSxKpY";
    private UserService userService = new UserServiceImpl();

    public boolean isValidCredentials(UserLoginDto userLoginDto) {
        if (isLoginDataValid(userLoginDto)) {
            String encryptedLoginPassword = DigestUtils.md5Hex(userLoginDto.getPassword().concat(salt));
            User user = userService.getUserByEmail(userLoginDto.getEmail());

            return user != null && encryptedLoginPassword.equals(user.getPassword());
        }
        return false;
    }

    public boolean registerValidation(UserRegistrationDto userForRegister) {

        User user = userService.getUserByEmail(userForRegister.getEmail());

        if (user == null && isUserDataValid(userForRegister)) {
            String password = DigestUtils.md5Hex(userForRegister.getPassword().concat(salt));
            System.out.println(password);
            user = new User(userForRegister.getEmail(),
                    userForRegister.getFirstName(),
                    userForRegister.getLastName(),
                    password);
            return userService.createUser(user) != null;
        }
        return false;
    }

    private boolean isUserDataValid(UserRegistrationDto user) {
        return user.getEmail().matches(EMAIL_REGEX)
                && user.getFirstName().length() >= MIN_LENGTH_FIRST_NAME
                && user.getLastName().length() >= MIN_LENGTH_LAST_NAME
                && user.getPassword().length() >= MIN_LENGTH_PASSWORD
                && user.getConfirmPassword().length() >= MIN_LENGTH_PASSWORD
                && user.getConfirmPassword().equals(user.getPassword());

    }

    private boolean isLoginDataValid(UserLoginDto userLoginDto) {
        return userLoginDto != null
                && userLoginDto.getEmail() != null && userLoginDto.getEmail().matches(EMAIL_REGEX)
                && userLoginDto.getPassword() != null && userLoginDto.getPassword().length() >= MIN_LENGTH_PASSWORD;
    }

    public static boolean isEmailValid(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr);
        return matcher.find();
    }
}
