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
        return taskServiceUsingDto.save(taskDto);
    }

    @PutMapping(path = "/{task_id}")
    @ResponseStatus(HttpStatus.OK)
    public TaskDto updateTask(@RequestBody TaskDto taskDto) {
        return taskDto;

    }

    @DeleteMapping(path = "/{task_id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteTask(@PathVariable Long id) {
        taskServiceUsingDto.remove(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TaskDto> getAllTasks() {
        return taskServiceUsingDto.getAll();
    }

    @GetMapping(path = "/{task_id}")
    @ResponseStatus(HttpStatus.OK)
    public TaskDto getTaskById(@PathVariable("task_id") Long taskId) {
        return null;
    }

    @GetMapping(path = "{owner_id}/owners-tasks")
    @ResponseStatus(HttpStatus.OK)
    public TaskDto getOwnerTasks(@PathVariable("owner_id") Long ownerId) {
        return null;
    }

    @GetMapping(path = "{worker_id}/workers-tasks")
    @ResponseStatus(HttpStatus.OK)
    public List<TaskDto> getWorkerTasks(@PathVariable("worker_id") Long workerId) {
        return new ArrayList<>();
    }

    @GetMapping (path = "/{parent_id}/tasks")
    @ResponseStatus(HttpStatus.OK)
    public List<TaskDto> getSubTasks(@PathVariable("parent_id") Long parentId) {
        return new ArrayList<>();
    }

    @GetMapping (path = "/{task_status}/tasks")
    @ResponseStatus(HttpStatus.OK)
    public List<TaskDto> getTaskByStatus(@PathVariable("task_status") Long statusId) {
        return new ArrayList<>();
    }
}
