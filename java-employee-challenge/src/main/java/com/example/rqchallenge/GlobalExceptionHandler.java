package com.example.rqchallenge;

import com.example.rqchallenge.employees.EmployeeController;
import com.example.rqchallenge.employees.exception.RpChallengeException;
import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);

    @ExceptionHandler(RpChallengeException.class)
    @ResponseBody
    public ResponseEntity<String> handleRpChallengeException(RpChallengeException ex) {
        LOGGER.error("An RpChallengeException occurred: {}", ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("An error occurred: " + ex.getMessage());
    }

    @ExceptionHandler(RequestNotPermitted.class)
    @ResponseBody
    public ResponseEntity<String> handleRateLimitException(RequestNotPermitted ex) {
        LOGGER.warn("An RequestNotPermitted occurred: {}", ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS)
                .body("Request not permitted.");
    }
}

