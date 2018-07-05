package com.softserve.mosquito.dtos;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class TaskSimpleDto {

    private Long id;
    private String name;
    private String parentTask;
    private String owner;
    private String worker;
    private String estimation;
    private String priority;
    private String status;
    private String trelloId;

    public TaskSimpleDto(Long id, String name, String parentTask, String owner, String worker, String estimation, String priority, String status) {
        this.id = id;
        this.name = name;
        this.parentTask = parentTask;
        this.owner = owner;
        this.worker = worker;
        this.estimation = estimation;
        this.priority = priority;
        this.status = status;
    }

    public TaskSimpleDto(String name, String parentTask, String owner, String worker, String status, String trelloId) {
        this.name = name;
        this.parentTask = parentTask;
        this.owner = owner;
        this.worker = worker;
        this.status = status;
        this.trelloId = trelloId;
    }

    public TaskSimpleDto(String name, String owner, String worker, String status, String trelloId) {
        this.name = name;
        this.owner = owner;
        this.worker = worker;
        this.status = status;
        this.trelloId = trelloId;
    }

    public boolean isPresentInCollection(List<TaskSimpleDto> simpleDtos) {
        for (TaskSimpleDto taskSimpleDto : simpleDtos) {
            if (this.equals(taskSimpleDto)) return true;
        }
        return false;
    }
}
