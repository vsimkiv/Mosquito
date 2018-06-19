package com.softserve.mosquito.controllers;

import com.softserve.mosquito.dtos.TaskFullDto;
import com.softserve.mosquito.services.api.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/projects")
public class ProjectController {
    private TaskService taskService;

    @Autowired
    public ProjectController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskFullDto createProject(@RequestBody TaskFullDto taskFullDto) {
        return taskService.save(taskFullDto);
    }

    @PutMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TaskFullDto updateProject(@PathVariable("id") Long id, @RequestBody TaskFullDto taskFullDto) {
        return taskService.update(taskFullDto);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProject(@PathVariable("id") Long id) {
        taskService.delete(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TaskFullDto> getAllProjects(){
        return taskService.getAllProjects();
    }

    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TaskFullDto getProjectById(@PathVariable("id") Long id){
        return taskService.getById(id);
    }

    @GetMapping(path = "/owner/{owner_id}")
    @ResponseStatus(HttpStatus.OK)
    public List<TaskFullDto> getProjectsByOwner(@PathVariable("owner_id")Long ownerId){
        return taskService.getByOwner(ownerId);
    }
}
