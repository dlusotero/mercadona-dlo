package com.mercadonarest.infraestructure.api.http_errors;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
class ErrorMessage {

    private final String error;
    private final String message;
    private final Integer code;

    ErrorMessage(Exception exception, Integer code) {
        this.error = exception.getClass().getSimpleName();
        this.message = exception.getMessage();
        this.code = code;
    }

    ErrorMessage(Exception exception,String msg, Integer code) {
        this.error = exception.getClass().getSimpleName();
        this.message = msg;
        this.code = code;
    }

}
