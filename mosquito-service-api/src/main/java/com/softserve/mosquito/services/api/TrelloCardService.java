package com.softserve.mosquito.services.api;

import com.softserve.mosquito.dtos.TaskDto;

import java.util.List;

public interface TrelloCardService {

    void getTasksFromTrello();

    List<TaskDto> showAllNewTrelloTasks();

}
