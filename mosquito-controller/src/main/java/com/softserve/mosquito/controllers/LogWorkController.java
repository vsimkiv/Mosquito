package com.softserve.mosquito.controllers;

import com.softserve.mosquito.dtos.LogWorkDto;
import com.softserve.mosquito.services.api.LogWorkService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/log-work")
@Api(value = "Log work controller", description = "Controller for doing CRUD operation with log work")
public class LogWorkController {

    private LogWorkService logWorkService;

    @Autowired
    public LogWorkController(LogWorkService logWorkService) {
        this.logWorkService = logWorkService;
    }

    @PostMapping(path = "/{est_id}/log-works/{remaining}")
    @ApiOperation(value = "Add new log work for task", response = LogWorkDto.class)
    @ResponseStatus(HttpStatus.CREATED)
    public LogWorkDto createLogWork(@PathVariable("est_id") Long estId, @PathVariable("remaining") int remaining,
                                    @RequestBody LogWorkDto logWorkDto) {

        return logWorkService.save(estId, logWorkDto, remaining);
    }

    @GetMapping(path = "/{log-work_id}")
    @ResponseStatus(HttpStatus.OK)
    public LogWorkDto getLogWorkById(@PathVariable("log-work_id") Long logId) {
        return logWorkService.getById(logId);
    }

    @PutMapping(path = "/{log-work_id}/{remaining}")
    @ApiOperation(value = "Update log work", consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public LogWorkDto updateLogWork(@PathVariable("log-work_id") Long logId, @PathVariable("remaining") int remaining,
                                    @RequestBody LogWorkDto logWorkDto) {
        logWorkDto.setId(logId);
        return logWorkService.update(logWorkDto, remaining);
    }

    @DeleteMapping(path = "/{log-workId}")
    public HttpStatus deleteLogWork(@PathVariable("log-workId") Long logId) {
        logWorkService.delete(logId);
        return HttpStatus.OK;
    }
    @GetMapping(path = "/by-est/{estimation_id}")
    @ResponseStatus(HttpStatus.OK)
    public List<LogWorkDto> getLogWorksByByEstimation(@PathVariable("estimation_id") Long estimationId) {
        return logWorkService.getByEstimationId(estimationId);
    }
    @GetMapping(path = "/by-user/{user_id}")
    @ResponseStatus(HttpStatus.OK)
    public List<LogWorkDto> getLogWorksByUserId(@PathVariable("user_id") Long userId) {
        return logWorkService.getByUserId(userId);
    }
}