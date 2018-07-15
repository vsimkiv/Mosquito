package com.softserve.mosquito.dtos;

import lombok.*;


@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskDto {
    private Long id;
    private String name;
    private Long ownerId;
    private Long workerId;
    private EstimationDto estimation;
    private PriorityDto priority;
    private StatusDto status;
    private Long parentId;
    private String trelloId;
}
