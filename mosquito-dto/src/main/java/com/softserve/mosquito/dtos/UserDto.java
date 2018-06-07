package com.softserve.mosquito.dtos;

import java.util.Set;

public class UserDto {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private String confirmPassword;
    private Set<SpecializationDto> specializations;

    private UserDto() {
    }

    public Long getId() {
        return id;
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

    public static Builder newBuilder() {
        return new UserDto().new Builder();
    }

    public class Builder {

        private Builder() {
        }

        public Builder setId(Long id) {
            UserDto.this.id = id;
            return this;
        }

        public Builder setEmail(String email) {
            UserDto.this.email = email;
            return this;
        }

        public Builder setFirstName(String firstName) {
            UserDto.this.firstName = firstName;
            return this;
        }

        public Builder setLastName(String lastName) {
            UserDto.this.lastName = lastName;
            return this;
        }

        public Builder setPassword(String password) {
            UserDto.this.password = password;
            return this;
        }

        public Builder setConfirmPassword(String confirmPassword) {
            UserDto.this.confirmPassword = confirmPassword;
            return this;
        }

        public Builder setSpecializations(Set<SpecializationDto> specializations) {
            UserDto.this.specializations = specializations;
            return this;
        }

        public UserDto build() {
            return UserDto.this;
        }
    }
}
