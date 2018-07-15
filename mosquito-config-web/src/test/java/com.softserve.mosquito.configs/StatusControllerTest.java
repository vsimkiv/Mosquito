package com.softserve.mosquito.configs;

import com.softserve.mosquito.controllers.GlobalExceptionHandler;
import com.softserve.mosquito.controllers.StatusController;
import com.softserve.mosquito.dtos.StatusDto;
import com.softserve.mosquito.services.api.StatusService;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

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
public class StatusControllerTest {
    private static final Long UNKNOWN_ID = Long.MAX_VALUE;
    private MockMvc mockMvc;

    @Autowired
    private StatusService statusServiceMock;

    @InjectMocks
    private StatusController statusController;

    @InjectMocks
    private GlobalExceptionHandler globalExceptionHandler;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Configuration
    public static class Config {
        @Bean
        @Primary
        public StatusService statusService() {
            return mock(StatusService.class);
        }
    }

    public StatusControllerTest() {
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void createStatus_success() throws Exception {
        StatusDto statusDto = new StatusDto(1L, "TODO");
        when(statusServiceMock.save(eq(statusDto))).thenReturn(statusDto);
        mockMvc.perform(
                post("/api/statuses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(statusDto)))
                .andExpect(status().isCreated());

        verify(statusServiceMock, times(1)).save(any(StatusDto.class));
        verifyNoMoreInteractions(statusServiceMock);
    }

    @Test
    public void getStatusById_success() throws Exception {
        StatusDto statusDto = new StatusDto(3L, "Doing");

        when(statusServiceMock.getById(3L)).thenReturn(statusDto);
        mockMvc.perform(
                get("/api/statuses/{status_id}", statusDto.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.id", org.hamcrest.Matchers.is(3)))
                .andExpect(jsonPath("$.title", org.hamcrest.Matchers.is("Doing")));

        verify(statusServiceMock).getById(anyLong());
        verifyNoMoreInteractions(statusServiceMock);
    }

    @Test
    public void updateStatus_success() throws Exception {
        StatusDto statusDto = new StatusDto(1L, "TODO");
        when(statusServiceMock.getById(statusDto.getId())).thenReturn(statusDto);
        when(statusServiceMock.update(statusDto)).thenReturn(statusDto);

        mockMvc.perform(
                put("/api/statuses/{status_id}", statusDto.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(statusDto)))
                .andExpect(status().isOk());
        verify(statusServiceMock, times(1)).getById(statusDto.getId());
        verify(statusServiceMock, times(1)).update(any(StatusDto.class));
        verifyNoMoreInteractions(statusServiceMock);
    }

    @DirtiesContext
    @Test
    public void test_update_status_fail_404_not_found() throws Exception {
        StatusDto statusDto = new StatusDto(1L, "TODO");

        mockMvc.perform(
                put("/api/statuses/{status_id}", statusDto.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(statusDto)))
                .andExpect(status().isNotFound());
        verify(statusServiceMock, times(1)).getById(statusDto.getId());
        verifyNoMoreInteractions(statusServiceMock);
    }

    @DirtiesContext
    @Test
    public void deleteStatus_success() throws Exception {
        StatusDto status = new StatusDto(10L, "Doing");
        when(statusServiceMock.getById(status.getId())).thenReturn(status);
        doNothing().when(statusServiceMock).delete(status.getId());
        mockMvc.perform(
                delete("/api/statuses/{id}", status.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(status)))
                .andExpect(status().isNoContent());
        verify(statusServiceMock, times(1)).getById(status.getId());
        verify(statusServiceMock, times(1)).delete(status.getId());
        verifyNoMoreInteractions(statusServiceMock);
    }

    @Test
    @DirtiesContext
    public void test_delete_status_fail_404_not_found() throws Exception {
        StatusDto status = new StatusDto(10L, "Doing");

        doNothing().when(statusServiceMock).delete(status.getId());
        mockMvc.perform(
                delete("/api/statuses/{id}", status.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(status)))
                .andExpect(status().isNotFound());
        verify(statusServiceMock, times(1)).getById(status.getId());
        verifyNoMoreInteractions(statusServiceMock);
    }

    @DirtiesContext
    @Test
    public void getAllStatuses() throws Exception {
        List<StatusDto> statusDtos = new ArrayList<>(Arrays.asList(
                new StatusDto(1L, "TODO"),
                new StatusDto(2L, "Doing")
        ));

        when(statusServiceMock.getAll()).thenReturn(statusDtos);

        mockMvc.perform(
                get("/api/statuses"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", org.hamcrest.Matchers.is(1)))
                .andExpect(jsonPath("$[0].title", org.hamcrest.Matchers.is("TODO")))
                .andExpect(jsonPath("$[1].id", org.hamcrest.Matchers.is(2)))
                .andExpect(jsonPath("$[1].title", org.hamcrest.Matchers.is("Doing")));

        verify(statusServiceMock, times(1)).getAll();
        verifyNoMoreInteractions(statusServiceMock);
    }
}