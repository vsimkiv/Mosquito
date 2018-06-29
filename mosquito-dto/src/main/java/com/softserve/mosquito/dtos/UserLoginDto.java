package com.softserve.mosquito.dtos;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class UserLoginDto {
    @NotBlank
    private String email;

    @NotBlank
    private String password;
}
