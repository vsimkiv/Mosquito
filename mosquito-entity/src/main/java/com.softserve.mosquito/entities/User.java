package com.softserve.mosquito.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    private Long id;
    private String email;
    private String password;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private boolean confirmed;

    @OneToMany(mappedBy = "owner", targetEntity = Task.class, fetch = FetchType.LAZY)
    private List<Task> taskWhereUserIsOwner = new ArrayList<>();

    @OneToMany(mappedBy = "worker", targetEntity = Task.class, fetch = FetchType.LAZY)
    private List<Task> taskWhereUserIsWorker = new ArrayList<>();

    @OneToMany(mappedBy = "author", targetEntity = Comment.class, fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "author", targetEntity = LogWork.class, fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<LogWork> logWorks = new ArrayList<>();

    @OneToOne(mappedBy = "user")
    @JoinColumn(name = "id", referencedColumnName = "user_id")
    private TrelloInfo trelloInfo;

    @Singular
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(name = "users_has_specializations", joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "specialization_id")})
    private Set<Specialization> specializations = new HashSet<>();
}