package com.softserve.mosquito.entities.mongo;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@Document(collection = "TasksBoard")
public class TasksBoard {

    @Id
    private String id;

    @NonNull
    private Long userId;

    @NonNull
    private Long ownerId;

    @NonNull
    private List<TaskMongo> taskMongos;


    @Override
    public String toString() {
        return "TasksBoard{" +
                "id='" + id + '\'' +
                ", userId=" + userId +
                ", ownerId=" + ownerId +
                ", taskMongos=" + taskMongos +
                '}';
    }
}
