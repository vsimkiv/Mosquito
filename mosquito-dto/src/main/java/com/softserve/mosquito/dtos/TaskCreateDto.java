package com.softserve.mosquito.dtos;

import lombok.*;


@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskCreateDto {

    private Long id;
    private String name;
    private Long ownerId;
    private Long workerId;
    private Integer estimationTime;
    private Long priorityId;
    private Long statusId;
    private Long parentId;

}
