package com.softserve.mosquito.controllers;

import com.softserve.mosquito.dtos.TaskDto;
import com.softserve.mosquito.services.api.TaskServiceUsingDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/tasks")
public class TaskController {

    private TaskServiceUsingDto taskServiceUsingDto;

    @Autowired
    public TaskController(TaskServiceUsingDto taskServiceUsingDto) {
        this.taskServiceUsingDto = taskServiceUsingDto;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskDto createSubTaskOrProject(@RequestBody TaskDto taskDto) {
        return taskServiceUsingDto.create(taskDto);
    }

    @PutMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TaskDto updateTask(@PathVariable("id") Long id, @RequestBody TaskDto taskDto) {
        return taskServiceUsingDto.update(taskDto);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteTask(@PathVariable("id") Long id) {
        taskServiceUsingDto.delete(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TaskDto> getAllTasks() {
        return taskServiceUsingDto.readAll();
    }

    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TaskDto getTaskById(@PathVariable("id") Long taskId) {
        return null;
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
