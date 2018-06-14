package com.softserve.mosquito.services.impl;

import com.softserve.mosquito.dtos.StatusDto;
import com.softserve.mosquito.entities.Status;
import com.softserve.mosquito.repo.api.StatusRepo;
import com.softserve.mosquito.services.api.StatusService;
import com.softserve.mosquito.transformer.impl.StatusTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class StatusServiceImpl implements StatusService {

    private StatusRepo statusRepo;
    @Autowired
    public StatusServiceImpl(StatusRepo statusRepo) {
        this.statusRepo = statusRepo;
    }

    @Override
    @Transactional
    public StatusDto save(StatusDto statusDto){
        Status status = statusRepo.create(StatusTransformer.toEntity(statusDto));

        if(status == null){
            return null;
        }

        return StatusTransformer.toDTO(status);
    }

    @Override
    @Transactional
    public StatusDto getById(Long id){
        Status status = statusRepo.read(id);

        if(status == null){
            return null;
        }

        return StatusTransformer.toDTO(status);
    }


    @Override
    @Transactional
    public StatusDto update(StatusDto statusDto){
        Status updatedStatus = statusRepo.update(StatusTransformer.toEntity(statusDto));

        if (updatedStatus == null){
            return null;
        }

        return StatusTransformer.toDTO(updatedStatus);
    }
    @Override
    @Transactional
    public void delete(Long id){
        statusRepo.delete(id);
    }

    @Override
    @Transactional
    public StatusDto getByName(String title){
        List<StatusDto> allStatuses = getAll();

        if(allStatuses != null && !allStatuses.isEmpty()){
            for(StatusDto status: allStatuses){
                if(status.getTitle().equals(title)){
                    return status;
                }
            }
        }
        return null;
    }

    @Override
    @Transactional
    public List<StatusDto> getAll(){
        List<Status> statuses = statusRepo.getAll();
        if(statuses == null || statuses.isEmpty()){
            return null;
        }
        List<StatusDto> statusDtos = new ArrayList<>();

        for (Status status : statuses){
            statusDtos.add(StatusTransformer.toDTO(status));
        }

        return statusDtos;
    }

}
