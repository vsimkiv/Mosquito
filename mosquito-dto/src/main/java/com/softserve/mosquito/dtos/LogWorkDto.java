package com.softserve.mosquito.dtos;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LogWorkDto {
    private Long id;
    private String description;
    private Long userId;
    private int logged;
    private LocalDateTime lastUpdate;
    private Long estimationId;
}
