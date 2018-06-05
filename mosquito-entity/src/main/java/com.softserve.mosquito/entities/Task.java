package com.softserve.mosquito.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tasks")
public class Task implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Task parentTask;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    @ManyToOne
    @JoinColumn(name = "worker_id")
    private User worker;

    @OneToOne
    @JoinColumn(name = "estimation_id")
    private Estimation estimation;

    @ManyToOne
    @JoinColumn(name = "priority_id")
    private Priority priority;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status status;

    @OneToMany(mappedBy = "task", targetEntity = Comment.class)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "parentTask", targetEntity = Task.class)
    private List<Task> childTasks = new ArrayList<>();

    public Task() {
    }

    /**
     * Just short constructor
     */
    public Task(Long id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

    /**
     * DB Insert constructor
     */
    public Task(String name, Long parentId, Long ownerId, Long workerId,
                Estimation estimation, Priority priority, Status status) {
        super();
        /*this.name = name;
        this.parentId = parentId;
        this.ownerId = ownerId;
        this.workerId = workerId;
        this.estimation = estimation;
        this.priority = priority;
        this.status = status;*/
    }


    /**
     * DB Select constructor
     */
    public Task(Long id, Long parentId, Long ownerId, Long workerId, String name, Status status, Priority priority,
                Estimation estimation) {
        super();
        /*this.id = id;
        this.parentId = parentId;
        this.ownerId = ownerId;
        this.workerId = workerId;
        this.name = name;
        this.status = status;
        this.priority = priority;
        this.estimation = estimation;*/
    }

    public Task(Long id, String name, Long parentId, Long ownerId, Long workerId,
                Estimation estimation, Priority priority, Status status, List<Comment> comments) {
        /*this.id = id;
        this.name = name;
        this.parentId = parentId;
        this.ownerId = ownerId;
        this.workerId = workerId;
        this.estimation = estimation;
        this.priority = priority;
        this.status = status;
        this.comments = comments;*/
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Estimation getEstimation() {
        return estimation;
    }

    public void setEstimation(Estimation estimation) {
        this.estimation = estimation;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", estimation=" + estimation +
                ", priority=" + priority +
                ", status=" + status +
                ", comments=" + comments +
                '}';
    }

    public Task getParentTask() {
        return parentTask;
    }

    public List<Task> getChildTasks() {
        return childTasks;
    }

    public void setChildTasks(List<Task> childTasks) {
        this.childTasks = childTasks;
    }

    public void setParentTask(Task parentTask) {
        this.parentTask = parentTask;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public User getWorker() {
        return worker;
    }

    public void setWorker(User worker) {
        this.worker = worker;
    }
}
