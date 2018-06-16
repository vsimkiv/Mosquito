package com.softserve.mosquito.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "confirmed")
    private boolean confirmed;

    @OneToMany(mappedBy = "owner", targetEntity = Task.class, fetch = FetchType.LAZY)
    private List<Task> taskWhereUserIsOwner = new ArrayList<>();

    @OneToMany(mappedBy = "worker", targetEntity = Task.class, fetch = FetchType.LAZY)
    private List<Task> taskWhereUserIsWorker = new ArrayList<>();

    @OneToMany(mappedBy = "author", targetEntity = Comment.class, fetch = FetchType.LAZY)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "author", targetEntity = LogWork.class, fetch = FetchType.LAZY)
    private List<LogWork> logWorks = new ArrayList<>();

    @OneToOne(mappedBy = "user")
    @JoinColumn(name = "id", referencedColumnName = "user_id")
    private TrelloInfo trelloInfo;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_has_specializations",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "specialization_id")})
    private Set<Specialization> specializations = new HashSet<>();

    public User() {

    }

    public User(Long id) {
        this.id = id;
    }

    public User(String email, String password,
                String firstName, String lastName, Set<Specialization> specializations) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.specializations = specializations;
    }

    public User(String email, String firstName, String lastName, String password) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }

    public User(Long id, boolean confirmed) {
        this.id = id;
        this.confirmed = confirmed;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    public Set<Specialization> getSpecializations() {
        return specializations;
    }

    public void setSpecializations(Set<Specialization> specializations) {
        this.specializations = specializations;
    }

    public List<Task> getTaskWhereUserIsOwner() {
        return taskWhereUserIsOwner;
    }

    public void setTaskWhereUserIsOwner(List<Task> taskWhereUserIsOwner) {
        this.taskWhereUserIsOwner = taskWhereUserIsOwner;
    }

    public List<Task> getTaskWhereUserIsWorker() {
        return taskWhereUserIsWorker;
    }

    public void setTaskWhereUserIsWorker(List<Task> taskWhereUserIsWorker) {
        this.taskWhereUserIsWorker = taskWhereUserIsWorker;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<LogWork> getLogWorks() {
        return logWorks;
    }

    public void setLogWorks(List<LogWork> logWorks) {
        this.logWorks = logWorks;
    }

    public TrelloInfo getTrelloInfo() {
        return trelloInfo;
    }

    public void setTrelloInfo(TrelloInfo trelloInfo) {
        this.trelloInfo = trelloInfo;
    }
}