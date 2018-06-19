package com.softserve.mosquito.controllers;

import com.softserve.mosquito.dtos.TaskSimpleDto;
import com.softserve.mosquito.services.api.TrelloCardService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trello")
public class TrelloCardController {

    private TrelloCardService trelloCardService;

    @Autowired
    public TrelloCardController(TrelloCardService trelloCardService) {
        this.trelloCardService = trelloCardService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<TaskSimpleDto>> showAllTrelloTasks(@PathVariable("userId") Long id) {
        return ResponseEntity.ok().body(trelloCardService.allNewTrelloTasks(id));
    }

    @GetMapping("/create/{userId}")
    public void getAllTrelloTasks(@PathVariable("userId") Long id) {
       trelloCardService.createTasksFromTrello(id);
    }

    @PostMapping("/create/{userId}")
    @ApiOperation(value = "Add new user", response = TaskSimpleDto.class)
    public ResponseEntity<List<TaskSimpleDto>> createUser(@RequestBody List<TaskSimpleDto> taskSimpleDtos, @PathVariable("userId") Long id) {

        trelloCardService.createChosenTastsFromTrello(id, taskSimpleDtos);

        return ResponseEntity.ok().body(taskSimpleDtos);
    }
}
