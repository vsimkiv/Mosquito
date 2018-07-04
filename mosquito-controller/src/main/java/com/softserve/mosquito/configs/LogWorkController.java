package com.softserve.mosquito.configs;

import com.softserve.mosquito.dtos.LogWorkDto;
import com.softserve.mosquito.services.api.LogWorkService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/log-work")
@Api(value = "Log work controller", description = "Controller for doing CRUD operation with log work")public class LogWorkController {

    private LogWorkService logWorkService;

    @Autowired
    public LogWorkController(LogWorkService logWorkService) {
        this.logWorkService = logWorkService;
    }

    @PostMapping(path = "/{task_id}/log-works/{remaining}")
    @ResponseStatus(HttpStatus.OK)
    public LogWorkDto createLogWork(@PathVariable("task_id") Long taskId,
                                    @PathVariable("remaining") int remaining,
                                           @RequestBody LogWorkDto logWorkDto) {
        return logWorkService.save(logWorkDto, remaining);
    }

    @GetMapping(path = "/{log-work_id}/{time-zone}")
    @ResponseStatus(HttpStatus.OK)
    public LogWorkDto getLogWorkById(@PathVariable("log-work_id") Long logId, @PathVariable("time-zone") byte timeZone) {
        return logWorkService.getById(logId,timeZone);
    }

    @PutMapping(path = "/{log-work_id}/{remaining}")
    @ResponseStatus(HttpStatus.OK)
    public LogWorkDto updateLogWork(@PathVariable("log-work_id") Long logId,
                                    @PathVariable("remaining") int remaining,
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