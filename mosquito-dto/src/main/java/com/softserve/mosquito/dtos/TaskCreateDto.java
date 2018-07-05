package com.softserve.mosquito.dtos;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TaskCreateDto {

    private String name;
    private Long owner;
    private Long worker;
    private Integer estimation;
    private Long priority;
    private Long parent;


}
