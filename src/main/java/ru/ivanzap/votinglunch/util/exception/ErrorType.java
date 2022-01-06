package ru.ivanzap.votinglunch.util.exception;

import org.springframework.http.HttpStatus;

public enum ErrorType {
    VALIDATION_ERROR("error.validationError", HttpStatus.UNPROCESSABLE_ENTITY);

    private final String errorCode;
    private final HttpStatus status;

    ErrorType(String errorCode, HttpStatus status) {
        this.errorCode = errorCode;
        this.status = status;
    }
}
