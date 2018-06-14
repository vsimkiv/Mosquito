package com.softserve.mosquito.controllers;

import com.softserve.mosquito.dtos.SpecializationDto;
import com.softserve.mosquito.services.api.SpecializationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/specializations")
public class SpecializationController {
    private SpecializationService specializationService;

    @Autowired
    public SpecializationController(SpecializationService specializationService) {
        this.specializationService = specializationService;
    }

    @PostMapping
    public ResponseEntity<SpecializationDto> createSpecialization(@RequestBody SpecializationDto specializationDto){
        SpecializationDto createdSpecialization = specializationService.save(specializationDto);

        if(createdSpecialization == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

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
    public ResponseEntity<SpecializationDto> updateSpecialization(@PathVariable("specialization_id") Long specialization_id,
                                                                  @RequestBody SpecializationDto specializationDto){
        SpecializationDto specializationForUpdate = new SpecializationDto(specialization_id, specializationDto.getTitle());
        SpecializationDto updatedSpacialization = specializationService.update(specializationForUpdate);

        if(updatedSpacialization == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(updatedSpacialization);
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
    public ResponseEntity removeSpecialization(@PathVariable("specialization_id") Long specialization_id){
        specializationService.delete(specialization_id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}