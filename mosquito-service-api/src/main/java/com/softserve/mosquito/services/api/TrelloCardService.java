package com.softserve.mosquito.services.api;

import com.softserve.mosquito.dtos.TaskCreateDto;


import java.util.List;

public interface TrelloCardService {

    //List<TaskSimpleDto> getAllNewTrelloTasksOnFront(Long workerId);
    List<TaskCreateDto> getAllNewTrelloTasksOnFront(Long userId);

    void createTasksFromTrello(Long userId);

    //void createChosenTasksFromTrelloJSON(Long workerId, List<TaskSimpleDto> taskSimpleDtos);
    void createChosenTasksFromTrelloJSON(Long userId, List<TaskCreateDto> taskCreateDtoList);

}
