package com.softserve.mosquito.services;

import com.softserve.mosquito.api.Transformer;
import com.softserve.mosquito.dtos.StatusCreateDto;
import com.softserve.mosquito.dtos.StatusDto;
import com.softserve.mosquito.entities.Status;
import com.softserve.mosquito.repo.api.StatusRepo;
import com.softserve.mosquito.repo.impl.StatusRepoImpl;
import com.softserve.mosquito.repository.StatusTransformer;

import java.util.ArrayList;
import java.util.List;

public class StatusServiceImpl implements StatusService{

    private StatusRepo statusRepo = new StatusRepoImpl();
    private Transformer<Status, StatusCreateDto> statusCreateTransformer = new StatusTransformer.StatusCreate();
    private Transformer<Status, StatusDto> statusGenericTransformer = new StatusTransformer.StatusGeneric();

    public List<StatusDto> getAllStatus(){
        List<Status> statuses = statusRepo.readAll();

        if (statuses == null || statuses.isEmpty()){
            return null;
        }

        List<StatusDto> statusDtos = new ArrayList<>();
        for (Status status: statuses) {
            statusDtos.add(statusGenericTransformer.toDTO(status));
        }

        return statusDtos;
    }

    public StatusDto getStatusById(Long id){
        Status status = statusRepo.read(id);

        if(status == null){
            return null;
        }

        return statusGenericTransformer.toDTO(status);
    }

    public StatusDto createStatus(StatusCreateDto statusCreateDto){
        Status createdStatus = statusRepo.create(statusCreateTransformer.toEntity(statusCreateDto));

        if(createdStatus == null){
            return null;
        }

        return statusGenericTransformer.toDTO(createdStatus);
    }

    public StatusDto updateStatus(StatusDto statusDto){
        Status updatedStatus = statusRepo.update(statusGenericTransformer.toEntity(statusDto));

        if (updatedStatus == null){
            return null;
        }

        return statusGenericTransformer.toDTO(updatedStatus);
    }

    public void removeStatus(StatusDto statusForDelete){
        statusRepo.delete(statusGenericTransformer.toEntity(statusForDelete));
    }
}
