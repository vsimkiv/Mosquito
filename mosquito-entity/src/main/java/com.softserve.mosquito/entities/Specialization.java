package com.softserve.mosquito.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "specializations")
public final class Specialization implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    @EqualsAndHashCode.Include
    @NonNull
    private String title;

    @ManyToMany(mappedBy = "specializations")
    @EqualsAndHashCode.Exclude
    private Set<User> users = new HashSet<>();

    public Specialization(Long id, String title) {
        this.id = id;
        this.title = title;
    }
}
