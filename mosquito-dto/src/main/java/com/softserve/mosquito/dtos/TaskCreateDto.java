package com.softserve.mosquito.dtos;

import lombok.*;

import java.util.List;


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
    private String trelloId;


    public TaskCreateDto(String name, Long ownerId, Long workerId, Long statusId, Long parentId, String trelloId) {
        this.name = name;
        this.ownerId = ownerId;
        this.workerId = workerId;
        this.statusId = statusId;
        this.parentId = parentId;
        this.trelloId = trelloId;
    }

    public boolean isPresentInCollection(List<TaskCreateDto> taskCreateDtos) {
        for (TaskCreateDto taskCreateDto : taskCreateDtos) {
            if (this.equals(taskCreateDto)) return true;
        }
        return false;
    }

}
