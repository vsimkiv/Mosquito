package com.softserve.mosquito.dtos;


import lombok.*;

import java.util.List;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
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
