package com.softserve.mosquito.dtos;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EstimationDto {

    private Long id;
    private Integer timeEstimation;
    private Integer remaining;
    private Long taskId;

    public EstimationDto(Integer timeEstimation) {
        this.timeEstimation = timeEstimation;
    }
}