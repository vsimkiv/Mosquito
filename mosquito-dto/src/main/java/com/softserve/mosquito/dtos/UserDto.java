package com.softserve.mosquito.dtos;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Set;

public class UserDto {
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
    private Set<SpecializationDto> specializations;

    private UserDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassword() {
        return password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public Set<SpecializationDto> getSpecializations() {
        return specializations;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public void setSpecializations(Set<SpecializationDto> specializations) {
        this.specializations = specializations;
    }

    public static Builder newBuilder() {
        return new UserDto().new Builder();
    }

    public class Builder {

        private Builder() {
        }

        public Builder id(Long id) {
            UserDto.this.id = id;
            return this;
        }

        public Builder email(String email) {
            UserDto.this.email = email;
            return this;
        }

        public Builder firstName(String firstName) {
            UserDto.this.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            UserDto.this.lastName = lastName;
            return this;
        }

        public Builder password(String password) {
            UserDto.this.password = password;
            return this;
        }

        public Builder confirmPassword(String confirmPassword) {
            UserDto.this.confirmPassword = confirmPassword;
            return this;
        }

        public Builder specializations(Set<SpecializationDto> specializations) {
            UserDto.this.specializations = specializations;
            return this;
        }

        public UserDto build() {
            return UserDto.this;
        }
    }


}
