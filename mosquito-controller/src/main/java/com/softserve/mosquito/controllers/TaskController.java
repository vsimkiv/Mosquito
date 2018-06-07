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

    @GetMapping (path = "{id}/subtasks")
    @ResponseStatus(HttpStatus.OK)
    public List<TaskDto> getSubTasks(@PathVariable("id") Long id) {
        return taskService.getSubTasks(id);
    }

    @GetMapping(path = "{owner_id}/tasks-as-owner")
    @ResponseStatus(HttpStatus.OK)
    public TaskDto getOwnerTasks(@PathVariable("owner_id") Long ownerId) {
        return null;
    }

    @GetMapping(path = "{worker_id}/task-as-worker")
    @ResponseStatus(HttpStatus.OK)
    public List<TaskDto> getWorkerTasks(@PathVariable("worker_id") Long workerId) {
        return new ArrayList<>();
    }

    @GetMapping (path = "{status_id}/task-by-status")
    @ResponseStatus(HttpStatus.OK)
    public List<TaskDto> getTaskByStatus(@PathVariable("status_id") Long statusId) {
        return new ArrayList<>();
    }
}
