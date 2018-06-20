package com.softserve.mosquito.transformer;

import com.softserve.mosquito.dtos.EstimationDto;
import com.softserve.mosquito.entities.Estimation;
import com.softserve.mosquito.entities.Task;

public class EstimationTransformer  {
    private EstimationTransformer() {}

    public static Estimation toEntity(EstimationDto estimationDto) {
        if (estimationDto==null) return null;
        Task task = new Task();
        task.setId(estimationDto.getTaskId());
        return new Estimation(estimationDto.getId(),estimationDto.getTimeEstimation(),estimationDto.getRemaining(),
                task,LogWorkTransformer.toEntityList(estimationDto.getLogWorks()));
    }


    public static EstimationDto toDTO(Estimation estimation) {
        Long taskId = null;
        if(estimation.getTask() != null)
            taskId = estimation.getTask().getId();

        return new EstimationDto(estimation.getId(),estimation.getTimeEstimation(),estimation.getRemaining(), taskId,
               LogWorkTransformer.toDTOList(estimation.getLogWorks()));
    }

}
