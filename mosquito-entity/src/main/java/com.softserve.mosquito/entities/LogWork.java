package com.softserve.mosquito.entities;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

@Entity
@Table(name = "log_works")
public class LogWork implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    private Integer logged;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    private User author;

    @Column(name = "last_update", nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd@HH:mm:ss.SSSZ")
    private LocalDateTime lastUpdate;

    @PreUpdate
    protected void onUpdate() {
        lastUpdate = LocalDateTime.now(ZoneId.ofOffset("UTC", ZoneOffset.ofHours(0)));
    }

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "estimation_id", referencedColumnName = "id")
    private Estimation estimation;

    public LogWork() {
    }

    public LogWork(Long id, String description, Integer logged, User user,
                   Long estimationId, LocalDateTime lastUpdate) {
        this.id = id;
        this.description = description;
        this.lastUpdate = lastUpdate;
        this.author = user;
        this.logged = logged;
        Estimation estimation = new Estimation();
        estimation.setId(estimationId);
        this.estimation = estimation;


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
                ", lastUpdate=" + lastUpdate.toString() +
                '}';
    }


}
