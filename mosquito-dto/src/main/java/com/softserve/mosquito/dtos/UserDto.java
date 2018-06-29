package com.softserve.mosquito.dtos;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Set;

@Builder
@Getter
public class UserDto {
    @Setter
    private Long id;

    @Pattern(regexp = "^[_A-Za-z0-9-+]+(.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(.[A-Za-z0-9]+)*(.[A-Za-z]{2,}$)",
            message = "user.email.pattern")
    private String email;

    @Size(min = 2, message = "user.first_name.pattern")
    private String firstName;

    @Size(min = 2, message = "user.last_name.pattern")
    private String lastName;

    @Size(min = 8, message = "user.password.pattern")
    private String password;

    @NotBlank
    private String confirmPassword;
    @Singular
    private Set<SpecializationDto> specializations;
}
