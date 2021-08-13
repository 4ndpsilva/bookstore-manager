package com.aps.bookstoremanager.exception;

import com.aps.bookstoremanager.dto.ErrorDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return ResponseEntity.badRequest().body(getErrors(ex.getBindingResult(), status));
    }

    private List<ErrorDTO> getErrors(BindingResult bindingResult, HttpStatus httpStatus){
        final List<ErrorDTO> errors = new ArrayList<>();
        
        bindingResult.getFieldErrors().forEach(e -> {
            final ErrorDTO error = new ErrorDTO();
            error.setMessage(e.getDefaultMessage());
            error.setField(e.getField());
            error.setStatusCode(httpStatus.value());
            error.setOccurredAt(LocalDateTime.now());
            errors.add(error);
        });

        return errors;
    }
}