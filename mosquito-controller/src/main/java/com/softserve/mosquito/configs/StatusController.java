package com.softserve.mosquito.configs;

import com.softserve.mosquito.dtos.StatusDto;
import com.softserve.mosquito.services.api.StatusService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/api/statuses")
@Api(value = "status controller", description = "Controller for doing CRUD operation with status")public class StatusController {
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
        if(id == null){
            throw new NotFoundException("Status with id " + id + " not found");
        }
        return statusService.getById(id);
    }


    @PutMapping(path= "/{status_id}")
    @ResponseStatus(HttpStatus.OK)
    public StatusDto updateStatus(@PathVariable("status_id") Long id, @RequestBody StatusDto statusDto) {
        if(statusService.getById(id)== null){
            throw new NotFoundException("Status with id " + id + " not found");
        }
        statusDto.setId(id);
        return statusService.update(statusDto);
    }

    @DeleteMapping(path= "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStatus(@PathVariable("id") Long id) {
        if(statusService.getById(id) == null){
            throw  new StatusNotFoundException("Status with id " + id + " not found");
        }
        statusService.delete(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<StatusDto>  getAllStatuses(){
        return statusService.getAll();
    }
}

