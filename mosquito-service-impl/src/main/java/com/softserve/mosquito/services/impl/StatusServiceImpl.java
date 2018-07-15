package com.softserve.mosquito.services.impl;

import com.softserve.mosquito.dtos.StatusDto;
import com.softserve.mosquito.entities.Status;
import com.softserve.mosquito.repo.api.StatusRepo;
import com.softserve.mosquito.services.api.StatusService;
import com.softserve.mosquito.transformer.StatusTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class StatusServiceImpl implements StatusService {

    private StatusRepo statusRepo;

    @Autowired
    public StatusServiceImpl(StatusRepo statusRepo) {
        this.statusRepo = statusRepo;
    }

    @Transactional
    @Override
    public StatusDto save(StatusDto statusDto){
        Status status = statusRepo.create(StatusTransformer.toEntity(statusDto));

        if(status == null){
            return null;
        }

        return StatusTransformer.toDTO(status);
    }

    @Transactional
    @Override
    public StatusDto getById(Long id){
        Status status = statusRepo.read(id);

        if(status == null){
            return null;
        }

        return StatusTransformer.toDTO(status);
    }

    @Transactional
    @Override
    public StatusDto update(StatusDto statusDto){
        Status updatedStatus = statusRepo.update(StatusTransformer.toEntity(statusDto));

        if (updatedStatus == null){
            return null;
        }

        return StatusTransformer.toDTO(updatedStatus);
    }

    @Transactional
    @Override
    public void delete(Long id){
        statusRepo.delete(id);
    }

    @Transactional
    @Override
    public StatusDto getByName(String title){
        List<StatusDto> allStatuses = getAll();

        if(allStatuses != null && !allStatuses.isEmpty()){
            for(StatusDto status: allStatuses){
                if(status.getTitle().equalsIgnoreCase(title)){
                    return status;
                }
            }
        }
        return null;
    }

    @Transactional
    @Override
    public List<StatusDto> getAll(){
        List<Status> statuses = statusRepo.getAll();
        if(statuses == null || statuses.isEmpty()){
            return Collections.emptyList();
        }
        List<StatusDto> statusDtos = new ArrayList<>();

        for (Status status : statuses){
            statusDtos.add(StatusTransformer.toDTO(status));
        }

        return statusDtos;
    }

}
