package com.softserve.mosquito.dtos;

import lombok.*;

import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonFormat(pattern = "yyyy-MM-dd@HH:mm:ss.SSSZ")
public class LogWorkDto {
    private Long id;
    private String description;
    private Long userId;
    private int logged;

    private LocalDateTime lastUpdate;
    private Long estimationId;
}
