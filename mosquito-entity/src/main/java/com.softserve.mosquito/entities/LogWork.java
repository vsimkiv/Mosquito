package com.softserve.mosquito.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@EqualsAndHashCode
@ToString
@Table(name = "log_works")
public class LogWork implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
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
}
