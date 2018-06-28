package com.softserve.mosquito.dtos;


import java.util.List;

public class TaskFullDto {
    private Long id;
    private String name;

    private TaskFullDto parentTaskFullDto;

    private UserDto ownerDto;
    private UserDto workerDto;

    private EstimationDto estimationDto;
    private PriorityDto priorityDto;
    private StatusDto statusDto;
    private String trelloId;

    private List<CommentDto> commentDtoList;
    private List<TaskFullDto> childTaskFullDtoList;

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

    public TaskFullDto getParentTaskFullDto() {
        return parentTaskFullDto;
    }

    public void setParentTaskFullDto(TaskFullDto parentTaskFullDto) {
        this.parentTaskFullDto = parentTaskFullDto;
    }

    public UserDto getOwnerDto() {
        return ownerDto;
    }

    public void setOwnerDto(UserDto ownerDto) {
        this.ownerDto = ownerDto;
    }

    public UserDto getWorkerDto() {
        return workerDto;
    }

    public void setWorkerDto(UserDto workerDto) {
        this.workerDto = workerDto;
    }

    public EstimationDto getEstimationDto() {
        return estimationDto;
    }

    public void setEstimationDto(EstimationDto estimationDto) {
        this.estimationDto = estimationDto;
    }

    public PriorityDto getPriorityDto() {
        return priorityDto;
    }

    public void setPriorityDto(PriorityDto priorityDto) {
        this.priorityDto = priorityDto;
    }

    public StatusDto getStatusDto() {
        return statusDto;
    }

    public void setStatusDto(StatusDto statusDto) {
        this.statusDto = statusDto;
    }

    public List<CommentDto> getCommentDtoList() {
        return commentDtoList;
    }

    public void setCommentDtoList(List<CommentDto> commentDtoList) {
        this.commentDtoList = commentDtoList;
    }

    public List<TaskFullDto> getChildTaskFullDtoList() {
        return childTaskFullDtoList;
    }

    public String getTrelloId() {
        return trelloId;
    }

    public void setTrelloId(String trelloId) {
        this.trelloId = trelloId;
    }

    public void setChildTaskFullDtoList(List<TaskFullDto> childTaskFullDtoList) {
        this.childTaskFullDtoList = childTaskFullDtoList;
    }

    public static TaskFullDtoBuilder builder() {
        return new TaskFullDtoBuilder();
    }

    public static class TaskFullDtoBuilder {
        private TaskFullDto taskFullDto;

        private TaskFullDtoBuilder() {
            taskFullDto = new TaskFullDto();
        }

        public TaskFullDtoBuilder id(long id) {
            taskFullDto.id = id;
            return this;
        }

        public TaskFullDtoBuilder name(String name) {
            taskFullDto.name = name;
            return this;
        }

        public TaskFullDtoBuilder parentTaskDto(TaskFullDto parentTaskFullDto) {
            if (parentTaskFullDto != null) {
                taskFullDto.parentTaskFullDto = parentTaskFullDto;
            }
            return this;
        }

        public TaskFullDtoBuilder ownerDto(UserDto ownerDto) {
            taskFullDto.ownerDto = ownerDto;
            return this;
        }


        public TaskFullDtoBuilder workerDto(UserDto workerDto) {
            taskFullDto.workerDto = workerDto;
            return this;
        }


        public TaskFullDtoBuilder estimationDto(EstimationDto estimationDto) {
            taskFullDto.estimationDto = estimationDto;
            return this;
        }

        public TaskFullDtoBuilder priorityDto(PriorityDto priorityDto) {
            taskFullDto.priorityDto = priorityDto;
            return this;
        }

        public TaskFullDtoBuilder statusDto(StatusDto statusDto) {
            taskFullDto.statusDto = statusDto;
            return this;
        }

        public TaskFullDtoBuilder commentDtoList(List<CommentDto> commentDtoList) {
            taskFullDto.commentDtoList = commentDtoList;
            return this;
        }

        public TaskFullDtoBuilder childTaskDtoList(List<TaskFullDto> childTaskFullDtoList) {
            taskFullDto.childTaskFullDtoList = childTaskFullDtoList;
            return this;
        }

        public TaskFullDto build(){
            return taskFullDto;
        }
    }
}
