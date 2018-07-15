package com.softserve.mosquito.transformer;

import com.softserve.mosquito.dtos.SpecializationDto;
import com.softserve.mosquito.dtos.TrelloInfoDto;
import com.softserve.mosquito.dtos.UserDto;
import com.softserve.mosquito.entities.TrelloInfo;
import com.softserve.mosquito.entities.User;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;


public class TrelloInfoTransformerTest {

    @Test
    public void toEntity() {
        SpecializationDto specializationDto1 = new SpecializationDto();
        specializationDto1.setId(2L);
        specializationDto1.setTitle("QC");
        Set<SpecializationDto> specializationDtos = new HashSet<>();
        specializationDtos.add(specializationDto1);
        UserDto userDto = UserDto.builder().email("test_email").password("test_passpword").id(3L).
                firstName("test_name").lastName("test_surname").
                specializations(specializationDtos).build();
        TrelloInfoDto trelloInfoDto = new TrelloInfoDto();
        trelloInfoDto.setId(2L);
        trelloInfoDto.setUserDto(userDto);
        trelloInfoDto.setUserTrelloKey("TrelloKey123456789");
        trelloInfoDto.setUserTrelloToken("TrelloToken123456789");
        trelloInfoDto.setUserTrelloName("TestTrelloName");
        TrelloInfo trelloInfo = TrelloInfoTransformer.toEntity(trelloInfoDto);
        assertEquals(trelloInfoDto.getUserTrelloKey(), trelloInfo.getUserTrelloKey());
    }

    @Test
    public void toDto() {
        TrelloInfo trelloInfo = new TrelloInfo();
        trelloInfo.setId(1L);
        trelloInfo.setUserTrelloToken("TrelloToken123456789");
        trelloInfo.setUserTrelloKey("TrelloKey123456789");
        trelloInfo.setUserTrelloName("TreloloNameTest");
        User user = new User();
        user.setId(5L);
        user.setEmail("test_email");
        user.setPassword("test_password");
        user.setFirstName("test_name");
        user.setLastName("test_surname");
        user.setConfirmed(true);
        trelloInfo.setUser(user);
        TrelloInfoDto trelloInfoDto = TrelloInfoTransformer.toDto(trelloInfo);
        assertEquals(trelloInfo.getId(), trelloInfoDto.getId());
        assertEquals(trelloInfo.getUser().getId(), trelloInfoDto.getUserDto().getId());
    }

    @Test
    public void toDtoList() {
        TrelloInfo trelloInfo = new TrelloInfo();
        trelloInfo.setId(4L);
        trelloInfo.setUserTrelloToken("TrelloToken123456789");
        trelloInfo.setUserTrelloKey("TrelloKey123456789");
        trelloInfo.setUserTrelloName("TreloloNameTest");
        User user = new User();
        user.setId(3L);
        user.setEmail("test_email");
        user.setPassword("test_password");
        user.setFirstName("test_name");
        user.setLastName("test_surname");
        user.setConfirmed(true);
        trelloInfo.setUser(user);
        List<TrelloInfo> trelloInfos = new ArrayList<>();
        trelloInfos.add(trelloInfo);
        List<TrelloInfoDto> trelloInfoDtos = TrelloInfoTransformer.toDto(trelloInfos);
        TrelloInfoDto trelloInfoDto = trelloInfoDtos.get(0);
        assertEquals(trelloInfo.getUser().getId(), trelloInfoDto.getUserDto().getId());
    }
    @Test
    public void toEntity_null() {
        TrelloInfoDto trelloInfoDto = null;
        TrelloInfo trelloInfo = TrelloInfoTransformer.toEntity(trelloInfoDto);
        assertNull(trelloInfo);
    }
    @Test
    public void toDto_null() {
        TrelloInfo trelloInfo = null;
        TrelloInfoDto trelloInfoDto = TrelloInfoTransformer.toDto(trelloInfo);
        assertNull(trelloInfoDto);
    }
}