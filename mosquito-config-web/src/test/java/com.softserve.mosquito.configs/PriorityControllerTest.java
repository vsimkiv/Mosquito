package com.softserve.mosquito.configs;

import com.softserve.mosquito.controllers.PriorityController;
import com.softserve.mosquito.dtos.PriorityDto;
import com.softserve.mosquito.services.api.PriorityService;
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


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebAppConfig.class, StatusControllerTest.Config.class})
@WebAppConfiguration
public class PriorityControllerTest {
    private static final Long UNKNOWN_ID = Long.MAX_VALUE;

    private MockMvc mockMvc;

    @Mock
    private PriorityService priorityServiceMock;

    @InjectMocks
    private PriorityController priorityController;

    public PriorityControllerTest() {
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(priorityController).build();
    }

    @Test
    public void createPriority_success() throws Exception {
        PriorityDto priorityDto = new PriorityDto(11L, "ForYesterday");
        when(priorityServiceMock.save(eq(priorityDto))).thenReturn(priorityDto);
        mockMvc.perform(
                post("/api/priorities")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(priorityDto)))
                .andExpect(status().isCreated());

        verify(priorityServiceMock, times(1)).save(any(PriorityDto.class));
        verifyNoMoreInteractions(priorityServiceMock);
    }

    @Test
    public void getPriorityById_success() throws Exception {
        PriorityDto priorityDto = new PriorityDto(11L, "ForYesterday");

        when(priorityServiceMock.getById(11L)).thenReturn(priorityDto);
        mockMvc.perform(
                get("/api/priorities/{priority_id}", priorityDto.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.id", org.hamcrest.Matchers.is(11)))
                .andExpect(jsonPath("$.title", org.hamcrest.Matchers.is("ForYesterday")));

        verify(priorityServiceMock).getById(anyLong());
        verifyNoMoreInteractions(priorityServiceMock);
    }

    @Test
    public void updatePriority_success() throws Exception {
        PriorityDto priorityDto = new PriorityDto(11L, "ForYesterday");
        when(priorityServiceMock.getById(priorityDto.getId())).thenReturn(priorityDto);
        when(priorityServiceMock.update(priorityDto)).thenReturn(priorityDto);

        mockMvc.perform(
                put("/api/priorities/{priority_id}", priorityDto.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(priorityDto)))
                .andExpect(status().isOk());
        verify(priorityServiceMock, times(1)).getById(priorityDto.getId());
        verify(priorityServiceMock, times(1)).update(any(PriorityDto.class));
        verifyNoMoreInteractions(priorityServiceMock);
    }
    @Test
    public void deletePriority_success() throws Exception {
        PriorityDto priorityDto = new PriorityDto(5L, "Highest");
        when(priorityServiceMock.getById(priorityDto.getId())).thenReturn(priorityDto);
        doNothing().when(priorityServiceMock).delete(priorityDto.getId());
        mockMvc.perform(
                delete("/api/priorities/{id}", priorityDto.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(priorityDto)))
                .andExpect(status().isNoContent());
        verify(priorityServiceMock, times(1)).getById(priorityDto.getId());
        verify(priorityServiceMock, times(1)).delete(priorityDto.getId());
        verifyNoMoreInteractions(priorityServiceMock);
    }
    @Test
    public void getAllPriorities() throws Exception {
        List<PriorityDto> priorityDtos = new ArrayList<>(Arrays.asList(
                new PriorityDto(1L, "High"),
                new PriorityDto(2L, "Low")
        ));

        when(priorityServiceMock.getAll()).thenReturn(priorityDtos);

        mockMvc.perform(
                get("/api/priorities"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", org.hamcrest.Matchers.is(1)))
                .andExpect(jsonPath("$[0].title", org.hamcrest.Matchers.is("High")))
                .andExpect(jsonPath("$[1].id", org.hamcrest.Matchers.is(2)))
                .andExpect(jsonPath("$[1].title", org.hamcrest.Matchers.is("Low")));

        verify(priorityServiceMock, times(1)).getAll();
        verifyNoMoreInteractions(priorityServiceMock);
    }
}