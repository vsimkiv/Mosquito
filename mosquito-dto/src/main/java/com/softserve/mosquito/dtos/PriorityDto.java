package com.softserve.mosquito.dtos;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PriorityDto implements Serializable{
    private Long id;
    private String title;
}