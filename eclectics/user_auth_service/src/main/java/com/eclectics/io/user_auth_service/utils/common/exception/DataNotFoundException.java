package com.eclectics.io.user_auth_service.utils.common.exception;

/**
 * trigger for data not found exception
 */
public class DataNotFoundException extends RuntimeException {
    public DataNotFoundException() {
        super();
    }

    public DataNotFoundException(String message) {
        super(message);
    }

    public DataNotFoundException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
