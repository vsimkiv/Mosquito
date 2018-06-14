package com.softserve.mosquito.controllers;

import com.softserve.mosquito.dtos.CommentDto;
import com.softserve.mosquito.dtos.TaskDto;
import com.softserve.mosquito.entities.mongo.Task;
import com.softserve.mosquito.services.api.CommentService;
import com.softserve.mosquito.services.api.TaskService;
import com.softserve.mosquito.services.api.TasksBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/tasks")
public class TaskController {

    private TaskService taskService;
    private CommentService commentService;
    private TasksBoardService tasksBoardService;


    @Autowired
    public TaskController(TaskService taskService, CommentService commentService, TasksBoardService tasksBoardService) {
        this.taskService = taskService;
        this.commentService = commentService;
        this.tasksBoardService = tasksBoardService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskDto createTask(@RequestBody TaskDto taskDto) {
        return taskService.save(taskDto);
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

    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TaskDto getTaskById(@PathVariable("id") Long id) {
        return taskService.getById(id);
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

    @GetMapping(path = "/{task_id}/comments")
    @ResponseStatus(HttpStatus.OK)
    public List<CommentDto> getCommentsByTaskId(@PathVariable("task_id") Long taskId){
        return commentService.getByTaskId(taskId);
    }

    @GetMapping(path = "/tasks-board")
    @ResponseStatus(HttpStatus.OK)
    public List<Task> mongoTest(@RequestParam(name = "worker_id") Long workerId) {
        /*tasksBoardService.add(new Task(100L, "task1"), 1L);
        tasksBoardService.add(new Task(101L, "task2"), 1L);
        tasksBoardService.add(new Task(102L, "task3"), 1L);

        tasksBoardService.add(new Task(103L, "task4"), 2L);
        */

        return tasksBoardService.getUserWork(workerId);
    }
}
