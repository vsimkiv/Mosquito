package com.softserve.mosquito.services.api;

import com.softserve.mosquito.dtos.TaskSimpleDto;

import java.util.List;

public interface TrelloCardService {

    List<TaskSimpleDto> showAllNewTrelloTasks(Long userId);

}
