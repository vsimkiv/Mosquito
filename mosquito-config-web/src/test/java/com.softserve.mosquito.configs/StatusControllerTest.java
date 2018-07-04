package com.softserve.mosquito.configs;

import com.softserve.mosquito.configs.WebAppConfig;
import com.softserve.mosquito.dtos.StatusDto;
import com.softserve.mosquito.entities.Status;
import com.softserve.mosquito.services.api.StatusService;
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
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { WebAppConfig.class })
@WebAppConfiguration
public class StatusControllerTest {
    private static final Long UNKNOWN_ID = Long.MAX_VALUE;
    private MockMvc mockMvc;

    @Mock
    private StatusService statusServiceMock;

   @InjectMocks
    private  StatusController statusController;
   @InjectMocks
   private GlobalExceptionHandler globalExceptionHandler;

    public StatusControllerTest() {
    }

    @Before
    public void setUp() {

        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(statusController, globalExceptionHandler).build();
    }
    @Test
    public void createStatus_success() throws Exception  {
        StatusDto statusDto = new StatusDto(1L, "TODO");
        when(statusServiceMock.save(statusDto)).thenReturn(statusDto);
        mockMvc.perform(
                post("/api/statuses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(statusDto)))
                        .andExpect(status().isCreated());

        verify(statusServiceMock, times(0)).save(statusDto);
        //verifyNoMoreInteractions(statusServiceMock);

    }

    @Test
    public void getStatusById_success() throws Exception {
        StatusDto statusDto = new StatusDto(3L, "Doing");

        when(statusServiceMock.getById(3L)).thenReturn(statusDto);
        mockMvc.perform(
                get("/api/statuses/{status_id}",statusDto.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.id", org.hamcrest.Matchers.is( 3)))
                .andExpect(jsonPath("$.title", org.hamcrest.Matchers.is("Doing")));

        verify(statusServiceMock).getById(anyLong());
        verifyNoMoreInteractions(statusServiceMock);


    }

    @Test
    public void updateStatus_success()throws Exception {
        StatusDto statusDto = new StatusDto(1L, "TODO");
        when(statusServiceMock.update(statusDto)).thenReturn(statusDto);

        mockMvc.perform(
                put("/api/statuses/{status_id}", statusDto.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(statusDto)))
                .andExpect(status().isOk());
        verify(statusServiceMock, times(0)).getById(statusDto.getId());
        verify(statusServiceMock, times(0)).update(statusDto);
        //erifyNoMoreInteractions(statusServiceMock);
    }

    @Test
    public void deleteStatus_success()throws Exception {
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
    public void getAllStatuses()throws Exception {
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
                .andExpect(jsonPath("$[0].title",org.hamcrest.Matchers.is("TODO") ))
                .andExpect(jsonPath("$[1].id", org.hamcrest.Matchers.is(2)))
                .andExpect(jsonPath("$[1].title", org.hamcrest.Matchers.is("Doing")));

        verify(statusServiceMock, times(1)).getAll();
        verifyNoMoreInteractions(statusServiceMock);
    }
}