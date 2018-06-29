package com.softserve.mosquito.entities.mongo;

import com.softserve.mosquito.entities.Task;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskMongo {
    private Long taskId;
    private String taskName;

    public TaskMongo(Task task) {
        this.taskId = task.getId();
        this.taskName = task.getName();
    }
}
