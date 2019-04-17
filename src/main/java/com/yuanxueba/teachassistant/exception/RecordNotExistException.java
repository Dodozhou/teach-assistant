package com.yuanxueba.teachassistant.exception;

public class RecordNotExistException extends Exception {
    private Integer errorCode;

    public RecordNotExistException() {
    }

    public RecordNotExistException(String message) {
        super(message);
    }

    public RecordNotExistException(String message, Integer errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public Integer getErrorCode() {
        return errorCode;
    }
}
