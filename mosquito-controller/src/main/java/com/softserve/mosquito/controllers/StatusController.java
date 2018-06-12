package com.softserve.mosquito.controllers;

import com.softserve.mosquito.dtos.StatusDto;
import com.softserve.mosquito.services.api.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/statuses")
public class StatusController {
    private StatusService statusService;

    @Autowired
    public StatusController(StatusService statusService) {
        this.statusService = statusService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StatusDto createStatus(@RequestBody StatusDto statusDto) {
        return statusService.save(statusDto);
    }

    @GetMapping(path= "/{status_id}")
    @ResponseStatus(HttpStatus.OK)
    public StatusDto getStatusById(@PathVariable("status_id") Long id){
        return statusService.getById(id);
    }


    @PutMapping(path= "/{status_id}")
    @ResponseStatus(HttpStatus.OK)
    public StatusDto updateStatus(@PathVariable("status_id") Long id, @RequestBody StatusDto statusDto) {
        statusDto.setId(id);
        return statusService.update(statusDto);
    }

    @DeleteMapping(path= "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteStatus(@PathVariable("id") Long id) {
        statusService.delete(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<StatusDto>  getAllStatuses(){
        return statusService.getAll();
    }
}

