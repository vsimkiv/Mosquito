package com.softserve.mosquito.configs;

import com.softserve.mosquito.controllers.SpecializationController;
import com.softserve.mosquito.dtos.SpecializationDto;
import com.softserve.mosquito.services.api.SpecializationService;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.*;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebAppConfig.class})
@WebAppConfiguration
public class SpecializationControllerTest {
    private static final Long UNKNOWN_ID = Long.MAX_VALUE;
    private MockMvc mockMvc;

    @Mock
    private SpecializationService specializationServiceMock;

    @InjectMocks
    private SpecializationController specializationController;


    public SpecializationControllerTest() {
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(specializationController).build();
    }

    @Test
    public void createSpecialization_success() throws Exception {
        SpecializationDto specializationDto = new SpecializationDto(8L, "Worker");
        when(specializationServiceMock.save(specializationDto)).thenReturn(specializationDto);
        mockMvc.perform(
                post("/api/specializations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(specializationDto)))
                .andExpect(status().isCreated());

        verify(specializationServiceMock, times(1)).save(any(SpecializationDto.class));
        verifyNoMoreInteractions(specializationServiceMock);
    }
    @Test
    public void getSpecializationById_success() throws Exception {
        SpecializationDto specializationDto = new SpecializationDto(4L, "Worker");

        when(specializationServiceMock.getById(specializationDto.getId())).thenReturn(specializationDto);
        mockMvc.perform(
                get("/api/specializations/{specialization_id}", specializationDto.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.id", org.hamcrest.Matchers.is(4)))
                .andExpect(jsonPath("$.title", org.hamcrest.Matchers.is("Worker")));

        verify(specializationServiceMock).getById(anyLong());
        verifyNoMoreInteractions(specializationServiceMock);
    }
    @Test
    public void updateSpecialization_success() throws Exception {
        SpecializationDto specializationDto = new SpecializationDto(4L, "Worker");
        when(specializationServiceMock.getById(specializationDto.getId())).thenReturn(specializationDto);
        when(specializationServiceMock.update(specializationDto)).thenReturn(specializationDto);

        mockMvc.perform(
                put("/api/specializations/{specialization_id}", specializationDto.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(specializationDto)))
                .andExpect(status().isOk());
        verify(specializationServiceMock, times(1)).getById(specializationDto.getId());
        verify(specializationServiceMock, times(1)).update(any(SpecializationDto.class));
        verifyNoMoreInteractions(specializationServiceMock);
    }
    @Test
    public void deleteSpecialization_success() throws Exception {
        SpecializationDto specializationDto = new SpecializationDto(10L, "Worker");
        when(specializationServiceMock.getById(specializationDto.getId())).thenReturn(specializationDto);
        doNothing().when(specializationServiceMock).delete(specializationDto.getId());
        mockMvc.perform(
                delete("/api/specializations/{specialization_id}", specializationDto.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(specializationDto)))
                .andExpect(status().isNoContent());
        verify(specializationServiceMock, times(1)).getById(specializationDto.getId());
        verify(specializationServiceMock, times(1)).delete(specializationDto.getId());
        verifyNoMoreInteractions(specializationServiceMock);
    }
    @Test
    public void getAllSpecializations() throws Exception {
        Set<SpecializationDto> specializationDtos = new HashSet<>(Arrays.asList(
                new SpecializationDto(1L, "Manager")
        ));

        when(specializationServiceMock.getAll()).thenReturn(specializationDtos);

        mockMvc.perform(
                get("/api/specializations"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", org.hamcrest.Matchers.is(1)))
                .andExpect(jsonPath("$[0].title", org.hamcrest.Matchers.is("Manager")));

        verify(specializationServiceMock, times(1)).getAll();
        verifyNoMoreInteractions(specializationServiceMock);
    }
}



