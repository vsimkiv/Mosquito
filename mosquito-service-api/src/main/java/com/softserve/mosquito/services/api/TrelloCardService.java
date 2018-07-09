package com.softserve.mosquito.services.api;

import com.softserve.mosquito.dtos.TaskCreateDto;


import java.util.List;

public interface TrelloCardService {

    List<TaskCreateDto> getAllNewTrelloTasksOnFront(Long userId);

    void createTasksFromTrello(Long userId);

    void createChosenTasksFromTrelloJSON(Long userId, List<TaskCreateDto> taskCreateDtoList);

}
