package com.softserve.mosquito.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "priorities")
public final class Priority implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Byte id;
    private String title;

    @JsonIgnore
    @OneToMany(mappedBy = "priority", fetch = FetchType.LAZY)
    private List<Task> tasks = new ArrayList<>();

    public Priority() {
    }

    public Priority(Byte id) {
        this.id = id;
    }

    public Priority(String title) {
        this.title = title;
    }

    public Priority(Byte id, String title) {
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
        return "Priority{" +
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


