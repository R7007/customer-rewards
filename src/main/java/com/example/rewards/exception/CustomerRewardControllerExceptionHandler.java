package com.example.rewards.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@Slf4j
@ControllerAdvice
public class CustomerRewardControllerExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<CustomerRewardAppError> exceptionHandler(Exception ex, WebRequest request) {
        log.error(ex.getMessage(), ex);
        CustomerRewardAppError message = new CustomerRewardAppError(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                new Date(),
                ex.getMessage(),
                request.getDescription(false));

        return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
