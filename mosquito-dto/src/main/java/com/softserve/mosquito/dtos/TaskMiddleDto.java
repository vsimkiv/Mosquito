package com.softserve.mosquito.dtos;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskMiddleDto {
    @Builder.Default
    private Long id;
    private String name;

    private Long parentId;

    private Long ownerId;
    private Long workerId;

    private Long estimationId;
    private Long priorityId;
    private Long statusId;
    private String trelloId;
}
