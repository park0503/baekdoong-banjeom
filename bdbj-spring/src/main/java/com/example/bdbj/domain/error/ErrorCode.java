package com.example.bdbj.domain.error;

public enum ErrorCode {
    MENU_NOT_UPDATED(400,"MENU NOT UPDATED"),
    ORDER_NOT_UPDATED(400,"ORDER NOT UPDATED"),
    MENU_NOT_FOUND(400, "MENU NOT FOUND"),
    ORDER_NOT_FOUND(400,"ORDER NOT FOUND"),
    MENU_NAME_DUPLICATION(400, "MENU NAME DUPLICATED"),
    INVALID_UUID_FORMAT(400, "INVALID UUID FORMAT"),
    INVALID_CATEGORY(400, "INVALID CATEGORY"),
    INVALID_ORDER_STATUS(400, "INVALID ORDER STATUS"),
    INVALID_PRICE(400, "INVALID PRICE"),
    FIELD_BLANK(400, "FIELD MUST NOT BLANK"),
    NOT_FOUND(404, "PAGE NOT FOUND"),
    INTERNAL_SERVER_ERROR(500, "INTERNAL SERVER ERROR");

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
