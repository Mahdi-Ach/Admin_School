package com.example.dashboardrest.GlobalException;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
@RestController
public class GlobalExceptions {
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<HashMap<String,String>> handleConstraintViolation(ConstraintViolationException ex) {
        System.out.println("xdd");
        HashMap<String,String> errors = new HashMap<>();
        for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
            String getField = String.valueOf(violation.getPropertyPath());
            String[] fieldname = getField.split("\\.");
                    errors.put(fieldname[1] ,violation.getMessage());
        }
    return ResponseEntity.badRequest().body(errors);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->{
                String field = error.getField();
                String errorCode = error.getCode();
                errors.put(field + "_" + errorCode, error.getDefaultMessage());
            }
        );
        return ResponseEntity.badRequest().body(errors);
    }
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<Map<String, String>> handleValidationParametreException(MissingServletRequestParameterException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put(ex.getParameterName(),ex.getMessage());
        return ResponseEntity.badRequest().body(errors);
    }
    @ExceptionHandler(validation_studentinfo.class)
    public ResponseEntity<HashMap<String,String>> handleDuplicateDataException(validation_studentinfo ex) {
        return new ResponseEntity<>(ex.getDetails(), HttpStatus.CONFLICT);
    }
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<HashMap<String,String>> CheckMultipartrequest(HttpMediaTypeNotSupportedException ex) {
        System.out.println(ex.getMessage());
        HashMap<String,String> erreur_type = new HashMap<>();
        erreur_type.put("unexpected","an expected erreur is encouterd");
        return ResponseEntity.badRequest().body(erreur_type);
    }
    @ExceptionHandler(MissingServletRequestPartException.class)
    public ResponseEntity<HashMap<String,String>> MissingFile(MissingServletRequestPartException ex){
        HashMap<String,String> missing_file = new HashMap<>();
        missing_file.put("file_erreur","file is required");
        return ResponseEntity.badRequest().body(missing_file);
    }
    @ExceptionHandler(NoUserFound.class)
    public ResponseEntity<String> NoUserFound(NoUserFound ex){
        return new ResponseEntity<>("User Does not exist", HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(InvalidGender.class)
    public ResponseEntity<HashMap<String,String>> Gendererreur(InvalidGender ex){
        HashMap<String,String> msg_update = new HashMap<>();
        msg_update.put("erreur_gender",ex.getMessage());
        return ResponseEntity.badRequest().body(msg_update);
    }

}