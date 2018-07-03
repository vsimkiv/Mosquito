package com.softserve.mosquito.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "estimation_id", referencedColumnName = "id")
    private Estimation estimation;

    @Column(name = "last_update")
    private LocalDateTime lastUpdate;

    @PreUpdate
    protected void onUpdate() {
        lastUpdate = LocalDateTime.now(ZoneId.ofOffset("UTC", ZoneOffset.ofHours(0)));
    }

    @Override
    public String toString() {
        return "LogWork{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", logged=" + logged +
                ", author=" + author +
                ", estimation=" + estimation +
                ", lastUpdate=" + lastUpdate +
                '}';
    }
}
