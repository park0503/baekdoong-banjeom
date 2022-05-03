package com.example.bdbj.exception;

import com.example.bdbj.domain.error.ErrorCode;

public class InvalidInputException extends RuntimeException{
    private final ErrorCode errorCode;


    public InvalidInputException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
