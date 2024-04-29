package com.training.gradesubmission;

import com.training.gradesubmission.expection.*;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<String> errors = new ArrayList<>();
        for (ObjectError objectError : ex.getBindingResult().getAllErrors()){
            errors.add(objectError.getDefaultMessage());
        }
        ErrorResponse errorResponse = new ErrorResponse(errors);
        //ex.getBindingResult().getAllErrors().forEach((error) -> errors.add(error.getDefaultMessage()));
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({CourseNotFoundException.class, GradeNotFoundException.class, StudentNotFoundException.class, StudentNotEnrolledException.class})
    public ResponseEntity<Object> handleResourceNotFoundException(RuntimeException ex){
        ErrorResponse errorResponse = new ErrorResponse(Arrays.asList(ex.getLocalizedMessage()));
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<Object> handleDataAccessException(EmptyResultDataAccessException ex) {
        ErrorResponse errorResponse = new ErrorResponse(Arrays.asList("Cannot delete non-existing resource"));
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex){
        ErrorResponse errorResponse = new ErrorResponse(Arrays.asList("Data Integrity Violation: we cannot process your request"));
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

}
