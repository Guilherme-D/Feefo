package com.example.feefo.configs.errorHandler;

import javassist.NotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request){
        return getExceptionMessage(ex.getMessage(), ex.getClass().getCanonicalName(), (ServletWebRequest) request, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleNotFoundException(NotFoundException ex, WebRequest request){
        return getExceptionMessage(ex.getMessage(), ex.getClass().getCanonicalName(), (ServletWebRequest) request, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleRuntimeException(RuntimeException ex, WebRequest request){
        return getExceptionMessage(ex.getMessage(), ex.getClass().getCanonicalName(), (ServletWebRequest) request, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Object> handleNoSuchElementException(NoSuchElementException ex, WebRequest request){
        return getExceptionMessage(ex.getMessage(), ex.getClass().getCanonicalName(), (ServletWebRequest) request, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAllExceptionMethod(Exception ex, WebRequest request) {
        return getExceptionMessage(ex.getMessage(), ex.getClass().getCanonicalName(), (ServletWebRequest) request, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<Object> getExceptionMessage(String message, String className, ServletWebRequest request, HttpStatus status) {
        ExceptionMessage exceptionMessageObj = new ExceptionMessage();
        exceptionMessageObj.setMessage(message);
        exceptionMessageObj.setError(className);
        exceptionMessageObj.setPath(request.getRequest().getServletPath());
        exceptionMessageObj.setStatusCode(status.value());
        return new ResponseEntity<>(exceptionMessageObj, new HttpHeaders(), status);
    }
}
