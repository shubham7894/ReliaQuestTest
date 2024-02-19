package com.example.rqchallenge.employees.exception;

public class RpChallengeException extends RuntimeException {

    public RpChallengeException() {
    }

    public RpChallengeException(String message) {
        super(message);
    }

    public RpChallengeException(String message, Throwable cause) {
        super(message, cause);
    }

    public RpChallengeException(Throwable cause) {
        super(cause);
    }

    public RpChallengeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
