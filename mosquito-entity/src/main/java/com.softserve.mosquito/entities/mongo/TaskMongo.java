package com.softserve.mosquito.entities.mongo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class TaskMongo {

    private Long taskId;
    private String taskName;
    private Long statusId;

    public TaskMongo(Long taskId, String taskName, Long statusId) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.statusId = statusId;
    }

}
