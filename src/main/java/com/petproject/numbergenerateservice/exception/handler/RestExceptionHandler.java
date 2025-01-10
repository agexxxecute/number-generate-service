package com.petproject.numbergenerateservice.exception.handler;

import com.petproject.numbergenerateservice.exception.InsufficientStorageException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

    @ResponseStatus(HttpStatus.INSUFFICIENT_STORAGE)
    @ExceptionHandler(InsufficientStorageException.class)
    public ErrorResponseDto handleInsufficientStorageException(RuntimeException runtimeException){
        return new ErrorResponseDto(runtimeException.getMessage());
    }
}
