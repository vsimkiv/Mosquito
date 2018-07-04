package com.softserve.mosquito.configs;

import com.softserve.mosquito.dtos.PriorityDto;
import com.softserve.mosquito.services.api.PriorityService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/priorities")
@Api(value = "Priority controller", description = "Controller for doing CRUD operation with task priority")
public class PriorityController {
    private PriorityService priorityService;

    @Autowired
    public PriorityController(PriorityService priorityService) {
        this.priorityService = priorityService;
    }

    @PostMapping
    public ResponseEntity<PriorityDto> createPriority(@RequestBody PriorityDto priorityDto){
        PriorityDto createdPriorityDto = priorityService.save(priorityDto);

        if(createdPriorityDto == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(createdPriorityDto);
    }

    @GetMapping("/{priority_id}")
    public ResponseEntity<PriorityDto> getPriorityById(@PathVariable("priority_id") Long id){
        PriorityDto priority = priorityService.getById(id);

        if(priority == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }else {
            return ResponseEntity.status(HttpStatus.OK).body(priority);
        }
    }


    @PutMapping(path = "/{priority_id}")
    public ResponseEntity<PriorityDto> updatePriority(@PathVariable("priority_id") Long id, @RequestBody PriorityDto priorityDto){
        priorityDto.setId(id);
        PriorityDto updatetedPriotityDto = priorityService.update(priorityDto);

        if(updatetedPriotityDto == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(updatetedPriotityDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PriorityDto> deletePriority(@PathVariable("id") Long id) {
        priorityService.delete(id);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping
    public ResponseEntity<List<PriorityDto>> getAllPriority(){
        List<PriorityDto>priorities = priorityService.getAll();

        if(priorities == null || priorities.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(priorities);
    }
}