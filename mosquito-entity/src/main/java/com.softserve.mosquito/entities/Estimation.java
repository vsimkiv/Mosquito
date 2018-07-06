package com.softserve.mosquito.entities;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "estimations")
public class Estimation implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(name = "estimation")
    private Integer timeEstimation;
    @Column(name = "remaining")
    private Integer remaining;

    @OneToOne(mappedBy = "estimation", fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "estimation_id")
    private Task task;

    @Singular
    @Fetch(FetchMode.SELECT)
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "estimation")
    private List<LogWork> logWorks;

    public Estimation(Integer timeEstimation, Integer remaining, Task task) {
        this.timeEstimation = timeEstimation;
        this.remaining = remaining;
        this.task = task;
    }

    @Override
    public String toString() {
        return "Estimation{" +
                "id=" + id +
                ", timeEstimation=" + timeEstimation +
                ", remaining=" + remaining +
                ", task=" + task +
                ", logWorks=" + logWorks +
                '}';
    }
}
