package com.softserve.mosquito.dtos;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CommentDto {

    private Long id;
    private String text;
    private Long authorId;
    private Long taskId;
    private LocalDateTime lastUpdate;
}