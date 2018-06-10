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
    private StatusTransformer transformer = new StatusTransformer();
    @Autowired
    public StatusServiceImpl(StatusRepo statusRepo) {
        this.statusRepo = statusRepo;
    }

    @Override
    @Transactional
    public StatusDto createStatus(StatusDto statusDto){
        Status status = statusRepo.create(transformer.toEntity(statusDto));

        if(status == null){
            return null;
        }

        return transformer.toDTO(status);
    }

    @Override
    @Transactional
    public StatusDto getStatusById(Long id){
        Status status = statusRepo.read(id);

        if(status == null){
            return null;
        }

        return transformer.toDTO(status);
    }


    @Override
    @Transactional
    public StatusDto updateStatus(StatusDto statusDto){
        Status updatedStatus = statusRepo.update(transformer.toEntity(statusDto));

        if (updatedStatus == null){
            return null;
        }

        return transformer.toDTO(updatedStatus);
    }
    @Override
    @Transactional
    public void removeStatus(Long id){
        statusRepo.delete(id);
    }

    @Override
    @Transactional
    public StatusDto getStatusByName(String title){
        List<StatusDto> allStatuses = getAllStatus();

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
    public Status getStatusEntityById(Long id) {
        Status status = statusRepo.read(id);

        if(status == null){
            return null;
        }

        return status;
    }

    @Override
    @Transactional
    public List<StatusDto> getAllStatus(){
        List<Status> statuses = statusRepo.readAll();
        if(statuses == null || statuses.isEmpty()){
            return null;
        }
        List<StatusDto> statusDtos = new ArrayList<>();

        for (Status status : statuses){
            statusDtos.add(transformer.toDTO(status));
        }

        return statusDtos;
    }

}
