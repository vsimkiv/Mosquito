package com.softserve.mosquito.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "statuses")
public class Status implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String title;

    @OneToMany(mappedBy = "status", fetch=FetchType.LAZY)
    private List<Task> tasks = new ArrayList<>();

    public Status(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Status status = (Status) o;
        return Objects.equals(id, status.id) &&
                Objects.equals(title, status.title) &&
                Objects.equals(tasks, status.tasks);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, title, tasks);
    }
}
