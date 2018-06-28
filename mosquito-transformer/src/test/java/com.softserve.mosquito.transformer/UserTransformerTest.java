package com.softserve.mosquito.transformer;

import com.softserve.mosquito.dtos.SpecializationDto;
import com.softserve.mosquito.dtos.UserDto;
import com.softserve.mosquito.entities.User;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class UserTransformerTest {

    @Test
   public void toEntity() {
        SpecializationDto specializationDto1 = new SpecializationDto();
        specializationDto1.setTitle("QC");
        Set<SpecializationDto> specializationDtos = new HashSet<>();
        UserDto userDto = UserDto.newBuilder().email("test_email@gmail.com").firstName("TestName").
                lastName("TestLastName").password("TestPas12345").confirmPassword("TestPas12345").
                specializations(specializationDtos).build();
        User user = UserTransformer.toEntity(userDto);
        assertEquals(userDto.getFirstName(), user.getFirstName());
        assertEquals(userDto.getEmail(), user.getEmail());
    }

    @Test
    public void toDTO() {
        User user = new User();
        user.setEmail("test_email");
        user.setPassword("test_password");
        user.setFirstName("test_name");
        user.setLastName("test_surname");
        user.setConfirmed(true);
        UserDto userDto = UserTransformer.toDTO(user);
        assertEquals(user.getEmail(), userDto.getEmail());
    }

    @Test
   public void toEntityList() {
        SpecializationDto specializationDto = new SpecializationDto();
        specializationDto.setTitle("QC");
        Set<SpecializationDto> specializationDtos = new HashSet<>();
        UserDto userDto = UserDto.newBuilder().email("test_email@gmail.com").firstName("TestName").
                lastName("TestLastName").password("TestPas12345").confirmPassword("TestPas12345").
                specializations(specializationDtos).build();
        List<UserDto> userDtos = new ArrayList<>();
        userDtos.add(userDto);
        List<User> usersList = UserTransformer.toEntity(userDtos);
        User firstUser = usersList.get(0);
        assertEquals(userDto.getFirstName(), firstUser.getFirstName());
        assertEquals(userDto.getEmail(), firstUser.getEmail());
    }

    @Test
    public void toDTOList() {
        User user = new User();
        user.setEmail("test_email");
        user.setPassword("test_password");
        user.setFirstName("test_name");
        user.setLastName("test_surname");
        user.setConfirmed(true);
        List<User> users = new ArrayList<>();
        users.add(user);
        List<UserDto> userDto = UserTransformer.toDTO(users);
        UserDto userDto1 = userDto.get(0);
        assertEquals(user.getEmail(), userDto1.getEmail());

    }
    @Test
    public void toDTO_null() {
        User user = null;
        UserDto userDto = UserTransformer.toDTO(user);
        assertNull(userDto);
    }
    @Test
    public void toEntity_null() {
        UserDto userDto = null;
        User user = UserTransformer.toEntity(userDto);
        assertNull(user);
    }
}