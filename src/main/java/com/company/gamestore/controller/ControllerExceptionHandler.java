package com.company.gamestore.controller;

import com.company.gamestore.model.CustomErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * ControllerExceptionHandler will handle JSR 303 validation
 * errors within a @RestControllerAdvice component.
 */
@RestControllerAdvice
public class ControllerExceptionHandler {
    /**
     * MethodArgumentNotValidException is the exception thrown
     * when JSR303 validation fails.
     * @param e The error being thrown.
     * @return Returns a ResponseEntity, made up of a List of
     *         errors as well as an HttpStatus letting you know
     *         the entity was unprocessable (request status 422)
     *         due to business rules.
     */
    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ResponseEntity<List<CustomErrorResponse>> controllerValidationError(MethodArgumentNotValidException e) {
        // BindingResult holds the validation errors
        BindingResult result = e.getBindingResult();
        // Validation errors are stored in FieldError objects
        List<FieldError> fieldErrors = result.getFieldErrors();

        // Translate the FieldErrors to CustomErrorResponse
        List<CustomErrorResponse> errorResponseList = new ArrayList<>();

        for (FieldError fieldError : fieldErrors) {
            CustomErrorResponse errorResponse = new CustomErrorResponse(HttpStatus.UNPROCESSABLE_ENTITY.toString(), fieldError.getDefaultMessage());
            errorResponse.setTimestamp(LocalDateTime.now());
            errorResponse.setStatus(HttpStatus.UNPROCESSABLE_ENTITY.value());
            errorResponseList.add(errorResponse);
        }

        // Create and return the ResponseEntity
        return new ResponseEntity<>(errorResponseList, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    /**
     * IllegalArgumentNotValidException is the exception thrown
     * when a method is passed an Illegal Argument.
     * @param e The error being thrown.
     * @return Returns a ResponseEntity, made up of an errorResponse
     *         as well as an HttpStatus letting you know the entity
     *         was unprocessable.
     */
    @ExceptionHandler(value = {IllegalArgumentException.class})
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ResponseEntity<CustomErrorResponse> serviceLayerValidationError(IllegalArgumentException e) {
        CustomErrorResponse errorResponse = new CustomErrorResponse(HttpStatus.UNPROCESSABLE_ENTITY.toString(), e.getMessage());
        errorResponse.setTimestamp(LocalDateTime.now());
        errorResponse.setStatus(HttpStatus.UNPROCESSABLE_ENTITY.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.UNPROCESSABLE_ENTITY);
    }


}