package com.softserve.mosquito.controllers;

import com.softserve.mosquito.entities.ErrorInfo;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TaskNotFoundException.class)
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public ErrorInfo taskNotFoundException(HttpServletRequest request, TaskNotFoundException exception) {
        String errorUrl = request.getRequestURL().toString();
        String message = exception.getMessage();
        return new ErrorInfo(message, errorUrl, HttpStatus.NOT_FOUND.value());
    }

    @ExceptionHandler(CommentNotFoundException.class)
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public ErrorInfo commentNotFound(HttpServletRequest request, CommentNotFoundException exception) {
        String errorUrl = request.getRequestURL().toString();
        String message = exception.getMessage();
        return new ErrorInfo(message, errorUrl, HttpStatus.NOT_FOUND.value());
    }
}

class TaskNotFoundException extends RuntimeException {

    public TaskNotFoundException(String message) {
        super(message);
    }
}

class CommentNotFoundException extends RuntimeException {

    public CommentNotFoundException(String message) {
        super(message);
    }
}