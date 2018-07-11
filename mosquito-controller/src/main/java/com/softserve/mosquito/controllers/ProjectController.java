package com.softserve.mosquito.controllers;

import com.softserve.mosquito.dtos.TaskCreateDto;
import com.softserve.mosquito.dtos.TaskDto;
import com.softserve.mosquito.services.api.TaskService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/projects")
@Api(value = "Project controller", description = "Controller for doing CRUD operation with project")
public class ProjectController {
    private TaskService taskService;

    @Autowired
    public ProjectController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskDto createProject(@RequestBody TaskCreateDto taskCreateDto) {
        return taskService.save(taskCreateDto);
    }

    @PutMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TaskDto updateProject(@PathVariable("id") Long id, @RequestBody TaskDto taskDto) {
        return taskService.update(taskDto);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProject(@PathVariable("id") Long id) {
        taskService.delete(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TaskDto> getAllProjects(){
        return taskService.getAllProjects();
    }

    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TaskDto getProjectById(@PathVariable("id") Long id){
        return taskService.getById(id);
    }

    @GetMapping(path = "/owner/{owner_id}")
    @ResponseStatus(HttpStatus.OK)
    public List<TaskDto> getProjectsByOwner(@PathVariable("owner_id")Long ownerId){
        return taskService.getProjectsByOwner(ownerId);
    }
}
