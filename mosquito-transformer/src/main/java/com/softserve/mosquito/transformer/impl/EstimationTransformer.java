package com.softserve.mosquito.transformer.impl;

import com.softserve.mosquito.dtos.EstimationDto;
import com.softserve.mosquito.entities.Estimation;
import com.softserve.mosquito.entities.Task;

public class EstimationTransformer  {
    private EstimationTransformer() {   throw new IllegalStateException("Utility class");}

    public static Estimation toEntity(EstimationDto estimationDto) {
        Task task = new Task();
        task.setId(estimationDto.getTaskId());
        return new Estimation(estimationDto.getId(),estimationDto.getTimeEstimation(),estimationDto.getRemaining(),
                task,LogWorkTransformer.toEntityList(estimationDto.getLogWorks()));
    }


    public static EstimationDto toDTO(Estimation estimation) {
        return new EstimationDto(estimation.getId(),estimation.getTimeEstimation(),estimation.getRemaining(),
               estimation.getTask().getId(),LogWorkTransformer.toDTOList(estimation.getLogWorks()));
    }

}
