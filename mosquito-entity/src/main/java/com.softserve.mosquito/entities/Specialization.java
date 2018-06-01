package com.softserve.mosquito.entities;

import java.io.Serializable;
import java.util.Objects;

public final class Specialization implements Serializable {
    private Byte id;
    private String title;

    public Specialization() {
    }

    public Specialization(String title) {
        this.title = title;
    }

    public Specialization(Byte id, String title) {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Specialization)) return false;
        Specialization that = (Specialization) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getTitle(), that.getTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle());
    }

    @Override
    public String toString() {
        return "Specialization{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
