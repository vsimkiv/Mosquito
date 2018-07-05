package com.softserve.mosquito.transformer;

import com.softserve.mosquito.dtos.EstimationDto;
import com.softserve.mosquito.entities.Estimation;
import com.softserve.mosquito.entities.Task;

public class EstimationTransformer  {

    private EstimationTransformer() {
        throw new IllegalStateException("Utility class");
    }

    public static Estimation toEntity(EstimationDto estimationDto) {
        if (estimationDto==null)
            return null;
        Task task = new Task();
        task.setId(estimationDto.getTaskId());
        if (estimationDto.getRemaining() == null)
            estimationDto.setRemaining(0);
        if (estimationDto.getTimeEstimation() == null)
            estimationDto.setTimeEstimation(0);
        return new Estimation(estimationDto.getTimeEstimation(),estimationDto.getRemaining(), task);
    }


    public static EstimationDto toDTO(Estimation estimation) {
        Long taskId = null;
        if(estimation == null){    return  null;
        }else

        if(estimation.getTask() != null)
            taskId = estimation.getTask().getId();
        if (estimation.getRemaining() == null)
            estimation.setRemaining(0);
        if (estimation.getTimeEstimation() == null)
            estimation.setTimeEstimation(0);

        return new EstimationDto(estimation.getId(),estimation.getTimeEstimation(),estimation.getRemaining(), taskId);
    }

}
