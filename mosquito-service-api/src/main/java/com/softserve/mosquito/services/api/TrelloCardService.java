package com.softserve.mosquito.services.api;

import com.softserve.mosquito.dtos.TaskCreateDto;
import com.softserve.mosquito.dtos.TaskSimpleDto;

import java.util.List;

public interface TrelloCardService {

    //List<TaskSimpleDto> allNewTrelloTasks(Long workerId);
    List<TaskCreateDto> allNewTrelloTasks(Long userId);

    void createTasksFromTrello(Long userId);

    //void createChosenTastsFromTrello(Long workerId, List<TaskSimpleDto> taskSimpleDtos);
    void createChosenTastsFromTrello(Long userId, List<TaskCreateDto> taskCreateDtoList);

}
