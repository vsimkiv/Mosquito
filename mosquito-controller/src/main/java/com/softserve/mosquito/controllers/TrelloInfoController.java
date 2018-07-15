package com.softserve.mosquito.controllers;


import com.softserve.mosquito.dtos.TrelloInfoDto;
import com.softserve.mosquito.services.api.TrelloInfoService;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/trelloInfo")
@Api(value = "TrelloInfo controller", description = "Controller for doing CRUD operation with trelloInfo")
public class TrelloInfoController {

    private TrelloInfoService trelloInfoService;

    @Autowired
    public TrelloInfoController(TrelloInfoService trelloInfoService) {
        this.trelloInfoService = trelloInfoService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TrelloInfoDto createTrelloInfo(@RequestBody TrelloInfoDto trelloInfoDto) {
        return trelloInfoService.save(trelloInfoDto);
    }

    @PutMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TrelloInfoDto updateTrelloInfo(@PathVariable("id") Long id, @RequestBody TrelloInfoDto trelloInfoDto) {
        return trelloInfoService.update(trelloInfoDto);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity deleteTrelloInfo(@PathVariable("id") Long id) {
        trelloInfoService.delete(id);
        if (trelloInfoService.getById(id) == null)
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.badRequest().build();
    }

    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TrelloInfoDto getTrelloInfoById(@PathVariable("id") Long id) {
        if (trelloInfoService.getById(id) == null)
            throw new TrelloInfoNotFoundException("TrelloInfo with Id " + id + " not found!");
        return trelloInfoService.getById(id);
    }

    @GetMapping(path = "/{id}/user")
    @ResponseStatus(HttpStatus.OK)
    public TrelloInfoDto getTrelloInfoByUserId(@PathVariable("id") Long id) {
        if (trelloInfoService.getByUserId(id) == null)
            throw new TrelloInfoNotFoundException("TrelloInfo with userId " + id + " not found!");
        return trelloInfoService.getByUserId(id);
    }

    @GetMapping(path = "/all")
    @ResponseStatus(HttpStatus.OK)
    public List<TrelloInfoDto> getAllTrelloInfos() {
        return trelloInfoService.getAll();
    }
}
