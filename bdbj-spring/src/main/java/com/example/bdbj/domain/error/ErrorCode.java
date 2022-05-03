package com.example.bdbj.domain.error;

public enum ErrorCode {
    MENU_NOT_UPDATED(400,"MENU NOT UPDATED"),
    MENU_NOT_FOUND(400, "MENU NOT FOUND"),
    MENU_NAME_DUPLICATION(400, "MENU NAME DUPLICATED");

    private final int status;
    private final String message;

    ErrorCode(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
