package com.example.bdbj.exception;

import com.example.bdbj.domain.error.ErrorCode;

public class ValueDuplicationException extends RuntimeException{
    private final ErrorCode errorCode;


    public ValueDuplicationException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
