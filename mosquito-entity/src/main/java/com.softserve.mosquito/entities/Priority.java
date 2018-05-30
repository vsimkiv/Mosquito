package com.softserve.mosquito.entities;

import java.io.Serializable;

public final class Priority implements Serializable {
    private Byte id;
    private String title;

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
}


