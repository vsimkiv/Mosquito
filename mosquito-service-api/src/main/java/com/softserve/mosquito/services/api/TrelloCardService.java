package com.softserve.mosquito.services.api;

import com.softserve.mosquito.entities.Task;

import java.util.List;

public interface TrelloCardService {

    void getTasksFromTrello();
    List<Task> showAllNewTrelloTasks();

}
