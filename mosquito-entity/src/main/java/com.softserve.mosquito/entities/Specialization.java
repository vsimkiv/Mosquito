package com.softserve.mosquito.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "specializations")
public final class Specialization implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String title;

    @ManyToMany(mappedBy = "specializations")
    private Set<User> users = new HashSet<>();

    public Specialization() { }

    public Specialization(String title) {
        this.title = title;
    }

    public Specialization(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<User> getUsers() { return users; }

    public void setUsers(Set<User> users) { this.users = users; }

    @Override
    public String toString() {
        return "Specialization{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
