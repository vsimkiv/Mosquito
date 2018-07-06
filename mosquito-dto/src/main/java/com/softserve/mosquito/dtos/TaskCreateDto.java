package com.softserve.mosquito.dtos;


import java.util.List;

//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//import org.codehaus.jackson.annotate.JsonIgnoreProperties;
//
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@JsonIgnoreProperties(ignoreUnknown = true)
public class TaskCreateDto {

    private Long id;
    private String name;
    private Long owner;
    private Long worker;
    private Integer estimation;
    private Long priority;
    private Long status;
    private Long parent;
    private String trelloId;

    public TaskCreateDto(String name, Long owner, Long worker, Integer estimation, Long priority) {
        this.name = name;
        this.owner = owner;
        this.worker = worker;
        this.estimation = estimation;
        this.priority = priority;
    }

    public boolean isPresentInCollection(List<TaskCreateDto> taskCreateDtos) {
        for (TaskCreateDto taskCreateDto : taskCreateDtos) {
            if (this.equals(taskCreateDto)) return true;
        }
        return false;
    }

    //for start

    public TaskCreateDto() {
    }

    public TaskCreateDto(Long id, String name, Long owner, Long worker, Integer estimation, Long priority, Long status, Long parent, String trelloId) {
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.worker = worker;
        this.estimation = estimation;
        this.priority = priority;
        this.status = status;
        this.parent = parent;
        this.trelloId = trelloId;
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

    public Long getOwner() {
        return owner;
    }

    public void setOwner(Long owner) {
        this.owner = owner;
    }

    public Long getWorker() {
        return worker;
    }

    public void setWorker(Long worker) {
        this.worker = worker;
    }

    public Integer getEstimation() {
        return estimation;
    }

    public void setEstimation(Integer estimation) {
        this.estimation = estimation;
    }

    public Long getPriority() {
        return priority;
    }

    public void setPriority(Long priority) {
        this.priority = priority;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public Long getParent() {
        return parent;
    }

    public void setParent(Long parent) {
        this.parent = parent;
    }

    public String getTrelloId() {
        return trelloId;
    }

    public void setTrelloId(String trelloId) {
        this.trelloId = trelloId;
    }


    //end

}
