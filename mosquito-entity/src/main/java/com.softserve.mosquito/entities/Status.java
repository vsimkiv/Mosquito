package com.softserve.mosquito.entities;

import java.io.Serializable;

public final class Status implements Serializable {
    private Byte id;
    private String title;

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

}
