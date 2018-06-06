package com.softserve.mosquito.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "statuses")
public final class Status implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Byte id;
    private String title;
    @OneToMany(mappedBy = "status")
    private List<Task> tasks = new ArrayList<>();

    public Status() {
    }

    public Status(Byte id) {
        this.id = id;
    }

    public Status(String title) {
        this.title = title;
    }

    public Status(Byte id, String title) {
        this.id = id;
        this.title = title;
    }

    public Byte getId() {
        return id;
    }

    public void setId(Byte id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Status{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}
