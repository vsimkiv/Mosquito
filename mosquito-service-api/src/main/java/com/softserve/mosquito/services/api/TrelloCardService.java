package com.softserve.mosquito.services.api;

import com.softserve.mosquito.dtos.TaskSimpleDto;

import java.util.List;

public interface TrelloCardService {

    void getTasksFromTrello();

    List<TaskSimpleDto> showAllNewTrelloTasks();

}
