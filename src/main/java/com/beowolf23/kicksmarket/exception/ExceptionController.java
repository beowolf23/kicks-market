package com.beowolf23.kicksmarket.exception;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@ControllerAdvice
@Slf4j
public class ExceptionController {

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(ConstraintViolationException.class)
    public Error handleConstraintViolation(ConstraintViolationException ex, HttpServletRequest httpServletRequest) {
        log.debug("Constraint violation exception encountered: {}", ex.getConstraintViolations(), ex);
        List<String> errorMessages = buildErrorMessages(ex.getConstraintViolations());
        return Error.builder()
                .timestamp(new Date(System.currentTimeMillis()))
                .status(HttpStatus.UNPROCESSABLE_ENTITY.toString())
                .errors(errorMessages.toArray(String[]::new))
                .path(httpServletRequest.getRequestURI()) // You might want to extract the path from the request
                .build();
    }

    private List<String> buildErrorMessages(Set<ConstraintViolation<?>> violations) {
        return violations.stream()
                .map(violation -> violation.getPropertyPath().toString() + ": " + violation.getMessage())
                .toList();
    }
}
