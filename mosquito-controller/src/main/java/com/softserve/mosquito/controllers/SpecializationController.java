package com.softserve.mosquito.controllers;

import com.softserve.mosquito.dtos.SpecializationDto;
import com.softserve.mosquito.exceptions.NotFoundException;
import com.softserve.mosquito.exceptions.StatusNotFoundException;
import com.softserve.mosquito.services.api.SpecializationService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/specializations")
@Api(value = "Specialization controller", description = "Controller for doing CRUD operation with specialization")
public class SpecializationController {
    private SpecializationService specializationService;

    @Autowired
    public SpecializationController(SpecializationService specializationService) {
        this.specializationService = specializationService;
    }

    @PostMapping
    public ResponseEntity<SpecializationDto> createSpecialization(@RequestBody SpecializationDto specializationDto){
        SpecializationDto createdSpecialization = specializationService.save(specializationDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdSpecialization);
    }

    @GetMapping("/{specialization_id}")
    public ResponseEntity<SpecializationDto> getSpecializationById(@PathVariable("specialization_id") Long specialization_id){
        SpecializationDto specialization = specializationService.getById(specialization_id);

        if(specialization == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(specialization);
    }

    @PutMapping("/{specialization_id}")
    @ResponseStatus(HttpStatus.OK)
    public SpecializationDto updateSpecialization(@PathVariable("specialization_id") Long specialization_id,
                                                                  @RequestBody SpecializationDto specializationDto){
        if(specializationService.getById(specialization_id)== null){
            throw new NotFoundException("Specialization with id " + specialization_id + " not found");
        }
        specializationDto.setId(specialization_id);
        return specializationService.update(specializationDto);
    }

    @GetMapping
    public ResponseEntity<Set<SpecializationDto>> getAllSpecializations(){
        Set<SpecializationDto> specializations = specializationService.getAll();

        if(specializations == null || specializations.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(specializations);
    }

    @DeleteMapping("/{specialization_id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeSpecialization(@PathVariable("specialization_id") Long specialization_id){
        if(specializationService.getById(specialization_id) == null){
            throw  new StatusNotFoundException("Specialization with id " + specialization_id + " not found");
        }
         specializationService.delete(specialization_id);
    }
}