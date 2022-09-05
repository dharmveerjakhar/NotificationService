package com.notificationservice.exception.handler;

import com.notificationservice.dto.response.ErrorDetails;
import com.notificationservice.dto.response.GenericResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@RestControllerAdvice
@Slf4j
public class ExceptionsHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException e) {
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        List<ErrorDetails> errors = new ArrayList<>();
        if (!violations.isEmpty()) {
            violations.forEach(violation -> errors.add(new ErrorDetails(violation.getMessage(), "INVALID_REQUEST")));
        } else {
            errors.add(new ErrorDetails("Oops some error has occurred", "INVALID_REQUEST"));
        }
        return new ResponseEntity<>(new GenericResponse(null, null, errors), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<String> errors = new ArrayList<>();
        e.getBindingResult().getFieldErrors().forEach(error -> {
            errors.add(error.getField() + " : " + error.getDefaultMessage());
        });

        return new ResponseEntity<>(new GenericResponse<>(null, errors, null), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Object> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        List<String> errors = Collections.singletonList(e.getCause().getMessage());
        List<ErrorDetails> errs = new ArrayList<>();
        errors.forEach(error -> errs.add(new ErrorDetails(error, "INVALID_REQUEST")));

        return new ResponseEntity<>(new GenericResponse<>(null, null, errs), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MissingPathVariableException.class)
    public ResponseEntity<Object> handleMissingPathVariableException(MissingPathVariableException e) {
        List<ErrorDetails> errors = Collections.singletonList(new ErrorDetails(e.getVariableName() + " of type " + e.getParameter().getNestedParameterType().getSimpleName() + " not found", "INVALID_REQUEST"));

        return new ResponseEntity<>(new GenericResponse<>(null, null, errors), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception e) {
        List<ErrorDetails> errors = Collections.singletonList(new ErrorDetails(e.getMessage(), "INTERNAL_ERROR"));
        return new ResponseEntity<>(new GenericResponse<>(null, null, errors), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}