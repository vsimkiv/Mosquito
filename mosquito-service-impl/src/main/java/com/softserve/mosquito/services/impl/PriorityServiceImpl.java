package com.softserve.mosquito.services.impl;

import com.softserve.mosquito.dtos.PriorityDto;
import com.softserve.mosquito.entities.Priority;
import com.softserve.mosquito.repo.api.PriorityRepo;
import com.softserve.mosquito.services.api.PriorityService;
import com.softserve.mosquito.transformer.PriorityTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class PriorityServiceImpl implements PriorityService {

    private PriorityRepo priorityRepo;

    @Autowired
    public PriorityServiceImpl(PriorityRepo priorityRepo) {
        this.priorityRepo = priorityRepo;
    }

    @Override
    public List<PriorityDto> getAll(){
        List<Priority> priorities = priorityRepo.getAll();

        if(priorities == null || priorities.isEmpty()){
            return null;
        }

        return PriorityTransformer.toDTOList(priorities);
    }

    @Override
    public PriorityDto getById(Long id){

        Priority priority = priorityRepo.read(id);

        if(priority == null){
            return null;
        }

        return PriorityTransformer.toDTO(priority);
    }

    @Override
    public PriorityDto save(PriorityDto priorityDto){
        Priority createdPriority = priorityRepo.create(PriorityTransformer.toEntity(priorityDto));

        if(createdPriority == null){
            return null;
        }

        return PriorityTransformer.toDTO(createdPriority);
    }

    @Override
    public PriorityDto update(PriorityDto priorityDto){
        Priority updatedPriority = priorityRepo.update(PriorityTransformer.toEntity(priorityDto));

        if(updatedPriority == null){
            return null;
        }

        return PriorityTransformer.toDTO(updatedPriority);
    }

    @Override
    public void delete(Long id){
        priorityRepo.delete(id);
    }
}
