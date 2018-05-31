package com.softserve.mosquito.services.impl;

import com.softserve.mosquito.api.Transformer;
import com.softserve.mosquito.dtos.PriorityCreateDto;
import com.softserve.mosquito.dtos.PriorityDto;
import com.softserve.mosquito.entities.Priority;
import com.softserve.mosquito.repo.api.PriorityRepo;
import com.softserve.mosquito.repo.impl.PriorityRepoImpl;
import com.softserve.mosquito.impl.PriorityTransformer;
import com.softserve.mosquito.services.api.PriorityService;

import java.util.ArrayList;
import java.util.List;


public class PriorityServiceImpl implements PriorityService {

    private PriorityRepo priorityRepo = new PriorityRepoImpl();
    private Transformer<Priority, PriorityCreateDto> priorityCreateTransformer = new PriorityTransformer.PriorityCreate();
    private Transformer<Priority, PriorityDto> priorityGenericTransformer = new PriorityTransformer.PriorityGeneric();

    @Override
    public List<PriorityDto> getAllPriorities(){
        List<Priority> priorities = priorityRepo.readAll();

        if(priorities == null || priorities.isEmpty()){
            return null;
        }

        List<PriorityDto> priorityDtos = new ArrayList<>();
        for(Priority priority: priorities){
            priorityDtos.add(priorityGenericTransformer.toDTO(priority));
        }

        return priorityDtos;
    }

    @Override
    public PriorityDto getPriorityById(Long id){
        Priority priority = priorityRepo.read(id);

        if(priority == null){
            return null;
        }

        return priorityGenericTransformer.toDTO(priority);
    }

    @Override
    public PriorityDto createPriority(PriorityCreateDto priorityCreateDto){
        Priority createdPriority = priorityRepo.create(priorityCreateTransformer.toEntity(priorityCreateDto));

        if(createdPriority == null){
            return null;
        }

        return priorityGenericTransformer.toDTO(createdPriority);
    }

    @Override
    public PriorityDto updatePriority(PriorityDto priorityDto){
        Priority updatedPriority = priorityRepo.update(priorityGenericTransformer.toEntity(priorityDto));

        if(updatedPriority == null){
            return null;
        }

        return priorityGenericTransformer.toDTO(updatedPriority);
    }

    @Override
    public void removePriority(Long id){
        priorityRepo.delete(id);
    }
}
