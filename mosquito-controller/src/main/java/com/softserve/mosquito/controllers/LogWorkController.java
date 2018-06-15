package com.softserve.mosquito.controllers;

import com.softserve.mosquito.dtos.LogWorkDto;
import com.softserve.mosquito.services.api.LogWorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/log-work")
public class LogWorkController {

    private LogWorkService logWorkService;

    @Autowired
    public LogWorkController(LogWorkService logWorkService) {
        this.logWorkService = logWorkService;
    }

    @PostMapping(path = "/{task_id}/log-works")
    @ResponseStatus(HttpStatus.OK)
    public LogWorkDto createLogWork(@PathVariable("task_id") Long taskId,
                                           @RequestBody LogWorkDto logWorkDto) {
        return logWorkService.save(logWorkDto);
    }

    @GetMapping(path = "/{log-work_id}/log-works")
    @ResponseStatus(HttpStatus.OK)
    public LogWorkDto getLogWorkById(@PathVariable("log-work_id") Long logId) {

        return logWorkService.getById(logId);
    }

    @PutMapping(path = "/{log-work_id}")
    @ResponseStatus(HttpStatus.OK)
    public LogWorkDto updateLogWork(@PathVariable("log-work_id") Long logId,
                                          @RequestBody LogWorkDto logWorkDto) {
        logWorkDto.setId(logId);
        return logWorkService.update(logWorkDto);
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