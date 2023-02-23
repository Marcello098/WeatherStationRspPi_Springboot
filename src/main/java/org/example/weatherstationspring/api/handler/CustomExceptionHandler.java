package org.example.weatherstationspring.api.handler;

import org.example.weatherstationspring.domain.measurement.exceptions.MeasurementNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(MeasurementNotFoundException.class)
    public final ResponseEntity<ErrorResponse> handleMeasurementNotFoundException(MeasurementNotFoundException exc) {
        return buildResponse(exc, HttpStatus.NOT_FOUND);
    }

    private <Ex extends RuntimeException> ResponseEntity<ErrorResponse> buildResponse(Ex exception, HttpStatus status) {
        return ResponseEntity
                .status(status)
                .body(new ErrorResponse(exception.getMessage()));
    }
}
