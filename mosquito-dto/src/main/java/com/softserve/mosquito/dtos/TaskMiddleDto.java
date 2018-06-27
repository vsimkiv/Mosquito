package com.softserve.mosquito.dtos;

import lombok.Builder;
import lombok.Singular;

import java.util.List;

@Builder
public class TaskMiddleDto {
    @Builder.Default private Long id;
    private String name;

    private Long parentId;

    private Long ownerId;
    private Long workerId;

    private Long estimationId;
    private Long priorityId;
    private Long statusId;
    private Long trelloId;

    @Singular("comments") private List<Long> commentIdList;
    @Singular("subtasks") private List<Long> subtasksIdList;
}
