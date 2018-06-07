package com.softserve.mosquito.controllers;

import com.softserve.mosquito.dtos.TaskDto;
import com.softserve.mosquito.services.api.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/tasks")
public class TaskController {

    private TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskDto createTask(@RequestBody TaskDto taskDto) {
        return taskService.create(taskDto);
    }

    @PutMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TaskDto updateTask(@PathVariable("id") Long id, @RequestBody TaskDto taskDto) {
        return taskService.update(taskDto);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteTask(@PathVariable("id") Long id) {
        taskService.delete(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TaskDto> getAllTasks() {
        return taskService.readAll();
    }

    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TaskDto getTaskById(@PathVariable("id") Long id) {
        return taskService.read(id);
    }

    @GetMapping (path = "filter/{parent_id}")
    @ResponseStatus(HttpStatus.OK)
    public List<TaskDto> getSubTasks(@PathVariable("parent_id") Long parentId) {
        return new ArrayList<>();
    }

    @GetMapping(path = "filter/{owner_id}")
    @ResponseStatus(HttpStatus.OK)
    public TaskDto getOwnerTasks(@PathVariable("owner_id") Long ownerId) {
        return null;
    }

    @GetMapping(path = "filter/{worker_id}")
    @ResponseStatus(HttpStatus.OK)
    public List<TaskDto> getWorkerTasks(@PathVariable("worker_id") Long workerId) {
        return new ArrayList<>();
    }

    @GetMapping (path = "filter/{status_id}")
    @ResponseStatus(HttpStatus.OK)
    public List<TaskDto> getTaskByStatus(@PathVariable("status_id") Long statusId) {
        return new ArrayList<>();
    }
}
