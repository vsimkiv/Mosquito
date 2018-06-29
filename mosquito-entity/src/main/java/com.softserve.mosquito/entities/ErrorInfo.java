package com.softserve.mosquito.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ErrorInfo {
    private String message;
    private String url;
    private int status;
}
