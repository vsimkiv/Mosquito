package com.softserve.mosquito.dtos;

import lombok.*;

import java.util.List;


@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
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

    public boolean isPresentInCollection(List<TaskCreateDto> taskCreateDtos) {
        for (TaskCreateDto taskCreateDto : taskCreateDtos) {
            if (this.getTrelloId().equals(taskCreateDto.getTrelloId())) return true;
        }
        return false;
    }
}
