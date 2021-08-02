package com.example.assignment.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class DefaultException extends IllegalStateException {

    private final String message;
    private Object[] args;
    private final int status;

    public DefaultException(String message) {
        super(message);
        this.message = message;
        this.status = HttpStatus.BAD_REQUEST.value();
    }
}
