package com.softserve.mosquito.entities;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "log_works")
public class LogWork implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String description;
    private Integer logged;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

    @Column(name = "last_update")
    private LocalDateTime lastUpdate;
    @ManyToOne
    @JoinColumn(name = "estimation_id")
    private Estimation estimation;

    public LogWork() {

        /*this.id = 0L;
        this.description = "";
        this.logged = 0;
        this.userId = 0L;
        this.estimation = new Estimation();*/
    }

    public LogWork(String description, Integer logged, Long userId, Long estimationId) {
        throw new NotImplementedException();

        /*this();
        this.description = description;
        this.logged = logged;
        this.userId = userId;
        this.estimation = null;//TODO*/
    }

    public LogWork(Long id, String description, Integer logged, Long userId,
                   Long estimationId, LocalDateTime lastUpdate) {
        throw new NotImplementedException();
        /*this.id = id;
        this.description = description;
        this.lastUpdate = lastUpdate;
        this.userId = userId;
        this.logged = logged;
        this.estimation = null;*/

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getLogged() {
        return logged;
    }

    public void setLogged(Integer logged) {
        this.logged = logged;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public String toString() {
        return "LogWork{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", logged=" + logged +
                ", lastUpdate=" + lastUpdate +
                '}';
    }

    public Estimation getEstimation() {
        return estimation;
    }

    public void setEstimation(Estimation estimation) {
        this.estimation = estimation;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }
}
