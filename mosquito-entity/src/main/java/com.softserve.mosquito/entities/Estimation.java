package com.softserve.mosquito.entities;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "estimations")
public class Estimation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
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
    @OneToMany(mappedBy = "estimation", cascade = CascadeType.ALL)
    private List<LogWork> logWorks;

    public Estimation(Integer timeEstimation, Integer remaining, Task task) {
        this.timeEstimation = timeEstimation;
        this.remaining = remaining;
        this.task = task;
    }
}
