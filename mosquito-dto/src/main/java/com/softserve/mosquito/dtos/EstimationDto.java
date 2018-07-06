package com.softserve.mosquito.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

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