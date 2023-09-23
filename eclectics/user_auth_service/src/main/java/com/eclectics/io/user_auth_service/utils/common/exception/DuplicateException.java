package com.eclectics.io.user_auth_service.utils.common.exception;

/**
 * trigger for duplicate exception
 */
public class DuplicateException extends RuntimeException {
    public DuplicateException() {
        super();
    }

    public DuplicateException(String message) {
        super(message);
    }

    public DuplicateException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
