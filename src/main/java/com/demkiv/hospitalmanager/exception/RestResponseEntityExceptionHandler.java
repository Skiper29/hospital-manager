package com.demkiv.hospitalmanager.exception;

import com.demkiv.hospitalmanager.exception.exceptions.CannotPerformOperationException;
import com.demkiv.hospitalmanager.exception.exceptions.ElementNotFoundException;
import com.demkiv.hospitalmanager.exception.exceptions.NoAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ResponseBody
@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ElementNotFoundException.class)
    private ResponseEntity<Object> handleElementNotFoundException(ElementNotFoundException exception) {
        return new ResponseEntity<>(
                exception.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NoAccessException.class)
    private ResponseEntity<Object> handleNoAccessException(NoAccessException exception) {
        return new ResponseEntity<>(
                exception.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CannotPerformOperationException.class)
    private ResponseEntity<Object> handleCannotPerformOperationException(CannotPerformOperationException exception) {
        return new ResponseEntity<>(
                exception.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
}
