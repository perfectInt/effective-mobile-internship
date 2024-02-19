package ru.effectivemobile.authservice.exception;

public class IncorrectCodeException extends RuntimeException {

    public IncorrectCodeException() {
    }

    public IncorrectCodeException(String message) {
        super(message);
    }

    public IncorrectCodeException(String message, Throwable cause) {
        super(message, cause);
    }

    public IncorrectCodeException(Throwable cause) {
        super(cause);
    }
}
