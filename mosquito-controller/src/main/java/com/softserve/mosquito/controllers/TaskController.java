package com.softserve.mosquito.controllers;

import com.softserve.mosquito.dtos.TaskCreateDto;
import com.softserve.mosquito.dtos.TaskDto;
import com.softserve.mosquito.entities.mongo.TaskMongo;
import com.softserve.mosquito.entities.mongo.TasksBoard;
import com.softserve.mosquito.services.api.TaskService;
import com.softserve.mosquito.services.api.TasksBoardService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/tasks")
@Api(value = "Task controller", description = "Controller for doing CRUD operation with task")
public class TaskController {

    private TaskService taskService;
    private TasksBoardService tasksBoardService;

    @Autowired
    public TaskController(TaskService taskService, TasksBoardService tasksBoardService) {
        this.taskService = taskService;
        this.tasksBoardService = tasksBoardService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskDto createTask(@RequestBody TaskCreateDto taskCreateDto) {
        TaskDto taskDto = taskService.save(taskCreateDto);
        tasksBoardService.add(new TaskMongo(taskDto.getId(), taskDto.getName(), taskDto.getStatus().getId()),
                taskDto.getWorkerId());
        taskService.sendPushMessage("<h3>You were assigned to the task! <br>Click here for extra information</h3>", taskDto.getWorkerId());
        return taskDto;
    }

    @PutMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TaskDto updateTask(@PathVariable("id") Long id, @RequestBody TaskCreateDto taskCreateDto) {
        tasksBoardService.update(new TaskMongo(taskCreateDto.getId(), taskCreateDto.getName(), taskCreateDto.getPriorityId()),
                taskCreateDto.getWorkerId());
        return taskService.update(taskCreateDto);
    }

    @DeleteMapping(path = "/{id}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity deleteTask(@PathVariable("id") Long id) {
        taskService.delete(id);
        if (taskService.getById(id) == null)
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.badRequest().build();

    }

    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TaskDto getTaskById(@PathVariable("id") Long id) {
        if (taskService.getSimpleTaskById(id) == null)
            throw new TaskNotFoundException("Task with Id " + id + " not found!");
        return taskService.getSimpleTaskById(id);
    }

    @GetMapping(path = "/{id}/info")
    @ResponseStatus(HttpStatus.OK)
    public TaskDto getFullTaskById(@PathVariable("id") Long id) {
        return taskService.getById(id);
    }

    @GetMapping(path = "{id}/sub-tasks")
    @ResponseStatus(HttpStatus.OK)
    public List<TaskDto> getSubTasks(@PathVariable("id") Long id) {
        return taskService.getSubTasks(id);
    }

    @GetMapping(path = "/workers-tasks/{worker_id}")
    @ResponseStatus(HttpStatus.OK)
    public List<TaskMongo> getWorkerTasks(@PathVariable("worker_id") Long workerId) {
        return tasksBoardService.getUserWork(workerId);
    }

    @GetMapping(path = "trello-task/{trello_id}")
    @ResponseStatus(HttpStatus.OK)
    public boolean isPresetn(@PathVariable("trello_id") String trelloId) {
        return taskService.isPresent(trelloId);
    }

    @GetMapping(path = "/worker-tasks")
    public List<TasksBoard> getByStatus(@RequestParam("status_id") Long statusId) {
        return tasksBoardService.getByStatusId(statusId);
    }

    @GetMapping(path = "/migrate")
    public void migrate() {
        tasksBoardService.migrateDbData();
    }

}