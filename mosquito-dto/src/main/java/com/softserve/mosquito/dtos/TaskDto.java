package com.softserve.mosquito.dtos;

public class TaskDto {
    private Long id;
    private String name;
    private Long parentId;

    private Long ownerId;
    private String firstNameOfOwner;
    private String lastNameOfOwner;

    private Long workerId;
    private String firstNameOfWorker;
    private String lastNameOfWorker;

    private Integer estimation;
    private Integer remaining;

    private Long priorityId;
    private String priorityTitle;

    private Long statusId;
    private String statusTitle;

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

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public String getFirstNameOfOwner() {
        return firstNameOfOwner;
    }

    public void setFirstNameOfOwner(String firstNameOfOwner) {
        this.firstNameOfOwner = firstNameOfOwner;
    }

    public String getLastNameOfOwner() {
        return lastNameOfOwner;
    }

    public void setLastNameOfOwner(String lastNameOfOwner) {
        this.lastNameOfOwner = lastNameOfOwner;
    }

    public Long getWorkerId() {
        return workerId;
    }

    public void setWorkerId(Long workerId) {
        this.workerId = workerId;
    }

    public String getFirstNameOfWorker() {
        return firstNameOfWorker;
    }

    public void setFirstNameOfWorker(String firstNameOfWorker) {
        this.firstNameOfWorker = firstNameOfWorker;
    }

    public String getLastNameOfWorker() {
        return lastNameOfWorker;
    }

    public void setLastNameOfWorker(String lastNameOfWorker) {
        this.lastNameOfWorker = lastNameOfWorker;
    }

    public Integer getEstimation() {
        return estimation;
    }

    public void setEstimation(Integer estimation) {
        this.estimation = estimation;
    }

    public Integer getRemaining() {
        return remaining;
    }

    public void setRemaining(Integer remaining) {
        this.remaining = remaining;
    }

    public Long getPriorityId() {
        return priorityId;
    }

    public void setPriorityId(Long priorityId) {
        this.priorityId = priorityId;
    }

    public String getPriorityTitle() {
        return priorityTitle;
    }

    public void setPriorityTitle(String priorityTitle) {
        this.priorityTitle = priorityTitle;
    }

    public Long getStatusId() {
        return statusId;
    }

    public void setStatusId(Long statusId) {
        this.statusId = statusId;
    }

    public String getStatusTitle() {
        return statusTitle;
    }

    public void setStatusTitle(String statusTitle) {
        this.statusTitle = statusTitle;
    }

    private TaskDtoBuilder builder() {
        return new TaskDtoBuilder();
    }

    public static class TaskDtoBuilder {
        private TaskDto taskDto;

        private TaskDtoBuilder() {
            taskDto = new TaskDto();
        }

        public TaskDtoBuilder id(long id) {
            taskDto.id = id;
            return this;
        }

        public TaskDtoBuilder name(String name) {
            taskDto.name = name;
            return this;
        }

        public TaskDtoBuilder parentId(Long parentId) {
            taskDto.parentId = parentId;
            return this;
        }

        public TaskDtoBuilder ownerId(Long ownerId) {
            taskDto.ownerId = ownerId;
            return this;
        }

        public TaskDtoBuilder firstNameOfOwner(String firstNameOfOwner) {
            taskDto.firstNameOfOwner = firstNameOfOwner;
            return this;
        }

        public TaskDtoBuilder lastNameOfOwner(String lastNameOfOwner) {
            taskDto.firstNameOfOwner = lastNameOfOwner;
            return this;
        }

        public TaskDtoBuilder workerId(Long workerId) {
            taskDto.workerId = workerId;
            return this;
        }

        public TaskDtoBuilder firstNameOfWorker(String firstNameOfWorker) {
            taskDto.firstNameOfWorker = firstNameOfWorker;
            return this;
        }

        public TaskDtoBuilder lastNameOfWorker(String lastNameOfWorker) {
            taskDto.firstNameOfWorker = lastNameOfWorker;
            return this;
        }

        public TaskDtoBuilder estimation(Integer estimation) {
            taskDto.estimation = estimation;
            return this;
        }

        public TaskDtoBuilder remaining(Integer remaining) {
            taskDto.remaining = remaining;
            return this;
        }

        public TaskDtoBuilder priorityId(Long priorityId) {
            taskDto.priorityId = priorityId;
            return this;
        }

        public TaskDtoBuilder priorityTitle(String priorityTitle) {
            taskDto.priorityTitle = priorityTitle;
            return this;
        }

        public TaskDtoBuilder statusId(Long statusId) {
            taskDto.statusId = statusId;
            return this;
        }

        public TaskDtoBuilder statusTitle(String statusTitle) {
            taskDto.statusTitle = statusTitle;
            return this;
        }
    }
}
