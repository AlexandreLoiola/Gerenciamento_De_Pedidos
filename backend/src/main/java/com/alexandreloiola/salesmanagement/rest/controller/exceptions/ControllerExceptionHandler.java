package com.alexandreloiola.salesmanagement.rest.controller.exceptions;

import com.alexandreloiola.salesmanagement.service.exceptions.BusinessRuleException;
import com.alexandreloiola.salesmanagement.service.exceptions.ConstraintException;
import com.alexandreloiola.salesmanagement.service.exceptions.DataIntegrityException;
import com.alexandreloiola.salesmanagement.service.exceptions.ObjectNotFoundException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(BusinessRuleException.class)
    public ResponseEntity<StandardError> businessRule(BusinessRuleException error, HttpServletRequest request) {
        StandardError err = new StandardError(
                                    System.currentTimeMillis(),
                                    HttpStatus.CONFLICT.value(),
                              "Regra de negócio",
                                    error.getMessage(),
                                    request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }

    @ExceptionHandler(ConstraintException.class)
    public ResponseEntity<StandardError> constraint(ConstraintException error, HttpServletRequest request) {
        StandardError err = new StandardError(
                                    System.currentTimeMillis(),
                                    HttpStatus.BAD_REQUEST.value(),
                                "Restrição de dados",
                                    error.getMessage(),
                                    request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @ExceptionHandler(DataIntegrityException.class)
    public ResponseEntity<StandardError> dataIntegrity(DataIntegrityException e, HttpServletRequest request) {
        StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), "Integridade de dados", e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }


    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<StandardError> handleDataIntegrityViolation(DataIntegrityViolationException e, HttpServletRequest request) {
        StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), "Integridade de dados", e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }


    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> ObjectNotFound(ObjectNotFoundException error, HttpServletRequest request) {
        StandardError err = new StandardError(
                                    System.currentTimeMillis(),
                                    HttpStatus.NOT_FOUND.value(),
                                "Não encontrado.",
                                    error.getMessage(),
                                    request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }
}
