package com.softserve.mosquito.controllers;

import com.softserve.mosquito.entities.ErrorInfo;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public ErrorInfo notFoundException(HttpServletRequest request, NotFoundException exception) {
        String errorUrl = request.getRequestURL().toString();
        String message = exception.getMessage();
        return new ErrorInfo(message, errorUrl, HttpStatus.NOT_FOUND.value());
    }


}

class TaskNotFoundException extends NotFoundException {

    public TaskNotFoundException(String message) {
        super(message);
    }
}

class CommentNotFoundException extends NotFoundException {

    public CommentNotFoundException(String message) {
        super(message);
    }
}
class StatusNotFoundException extends NotFoundException{
    public StatusNotFoundException(String message){super(message);}
}
class NotFoundException extends RuntimeException{
    public NotFoundException(String message){super(message);}
}