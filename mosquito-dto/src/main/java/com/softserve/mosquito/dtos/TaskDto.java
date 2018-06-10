package com.softserve.mosquito.dtos;


import java.util.List;

public class TaskDto {
    private Long id;
    private String name;

    private TaskDto parentTaskDto;

    private UserDto ownerDto;
    private UserDto workerDto;

    private EstimationDto estimationDto;
    private PriorityDto priorityDto;
    private StatusDto statusDto;

    private List<CommentCreateDto> commentDtoList;
    private List<TaskDto> childTaskDtoList;

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

    public TaskDto getParentTaskDto() {
        return parentTaskDto;
    }

    public void setParentTaskDto(TaskDto parentTaskDto) {
        this.parentTaskDto = parentTaskDto;
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

    public List<CommentCreateDto> getCommentDtoList() {
        return commentDtoList;
    }

    public void setCommentDtoList(List<CommentCreateDto> commentDtoList) {
        this.commentDtoList = commentDtoList;
    }

    public List<TaskDto> getChildTaskDtoList() {
        return childTaskDtoList;
    }

    public void setChildTaskDtoList(List<TaskDto> childTaskDtoList) {
        this.childTaskDtoList = childTaskDtoList;
    }

    public TaskDtoUsingOtherDtosBuilder builder() {
        return new TaskDtoUsingOtherDtosBuilder();
    }

    public static class TaskDtoUsingOtherDtosBuilder {
        private TaskDto taskDto;

        private TaskDtoUsingOtherDtosBuilder() {
            taskDto = new TaskDto();
        }

        public TaskDtoUsingOtherDtosBuilder id(long id) {
            taskDto.id = id;
            return this;
        }

        public TaskDtoUsingOtherDtosBuilder name(String name) {
            taskDto.name = name;
            return this;
        }

        public TaskDtoUsingOtherDtosBuilder parentTaskDto(TaskDto parentTaskDto) {
            if (parentTaskDto != null) {
                taskDto.parentTaskDto = parentTaskDto;
            }
            return this;
        }

        public TaskDtoUsingOtherDtosBuilder ownerDto(UserDto ownerDto) {
            taskDto.ownerDto = ownerDto;
            return this;
        }


        public TaskDtoUsingOtherDtosBuilder workerDto(UserDto workerDto) {
            taskDto.workerDto = workerDto;
            return this;
        }


        public TaskDtoUsingOtherDtosBuilder estimationDto(EstimationDto estimationDto) {
            taskDto.estimationDto = estimationDto;
            return this;
        }

        public TaskDtoUsingOtherDtosBuilder priorityDto(PriorityDto priorityDto) {
            taskDto.priorityDto = priorityDto;
            return this;
        }

        public TaskDtoUsingOtherDtosBuilder statusDto(StatusDto statusDto) {
            taskDto.statusDto = statusDto;
            return this;
        }

        public TaskDtoUsingOtherDtosBuilder commentDtoList(List<CommentCreateDto> commentDtoList) {
            taskDto.commentDtoList = commentDtoList;
            return this;
        }

        public TaskDtoUsingOtherDtosBuilder childTaskDtoList(List<TaskDto> childTaskDtoList) {
            taskDto.childTaskDtoList = childTaskDtoList;
            return this;
        }

        public TaskDto build(){
            return taskDto;
        }
    }
}
