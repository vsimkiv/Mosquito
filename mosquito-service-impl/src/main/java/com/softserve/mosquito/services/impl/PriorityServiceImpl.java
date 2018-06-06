package com.softserve.mosquito.services.impl;

import com.softserve.mosquito.transformer.api.Transformer;
import com.softserve.mosquito.dtos.PriorityCreateDto;
import com.softserve.mosquito.dtos.PriorityDto;
import com.softserve.mosquito.entities.Priority;
import com.softserve.mosquito.repo.api.PriorityRepo;
import com.softserve.mosquito.transformer.impl.PriorityTransformer;
import com.softserve.mosquito.services.api.PriorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PriorityServiceImpl implements PriorityService {

    private PriorityRepo priorityRepo;
    private Transformer<Priority, PriorityCreateDto> priorityCreateTransformer = new PriorityTransformer.PriorityCreate();
    private Transformer<Priority, PriorityDto> priorityGenericTransformer = new PriorityTransformer.PriorityGeneric();

    @Autowired
    public PriorityServiceImpl(PriorityRepo priorityRepo) {
        this.priorityRepo = priorityRepo;
    }

    @Override
    @Transactional
    public List<Priority> getAllPriorities(){
        List<Priority> priorities = priorityRepo.readAll();

        /*if(priorities == null || priorities.isEmpty()){
            return null;
        }

        List<PriorityDto> priorityDtos = new ArrayList<>();
        for(Priority priority: priorities){
            priorityDtos.add(priorityGenericTransformer.toDTO(priority));
        }

        return priorityDtos;*/
        return priorities;
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
