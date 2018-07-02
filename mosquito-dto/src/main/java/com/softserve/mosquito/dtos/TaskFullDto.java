package com.softserve.mosquito.dtos;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.Singular;

import java.util.List;

@Builder
@Setter
@Getter
public class TaskFullDto {
    private Long id;
    private String name;

    private TaskFullDto parentTaskFullDto;

    private UserDto ownerDto;
    private UserDto workerDto;

    private EstimationDto estimationDto;
    private PriorityDto priorityDto;
    private StatusDto statusDto;
    private String trelloId;

    @Singular("comments") private List<CommentDto> commentDtoList;
    @Singular("childTasks") private List<TaskFullDto> childTaskFullDtoList;
}
