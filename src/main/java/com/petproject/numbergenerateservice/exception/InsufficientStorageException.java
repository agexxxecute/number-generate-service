package com.petproject.numbergenerateservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INSUFFICIENT_STORAGE)
public class InsufficientStorageException extends RuntimeException {

    public InsufficientStorageException(String message) {
        super(message);
    }
}
