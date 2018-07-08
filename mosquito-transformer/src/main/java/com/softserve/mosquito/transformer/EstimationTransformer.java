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

        return Estimation.builder()
                .id(estimationDto.getId() == null ? null : estimationDto.getId())
                .remaining(estimationDto.getRemaining())
                .timeEstimation(estimationDto.getTimeEstimation())
                .build();
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
