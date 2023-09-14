package com.alexandreloiola.salesmanagement.rest.controller.exceptions;

import com.alexandreloiola.salesmanagement.service.exceptions.*;

import javax.servlet.http.HttpServletRequest;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionsDto> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, HttpServletRequest request) {
        String errorMsg = ex.getBindingResult().getFieldError().getDefaultMessage();
        ExceptionsDto exceptionsDto = new ExceptionsDto(
                System.currentTimeMillis(),
                HttpStatus.BAD_REQUEST.value(),
                "Requisição Inválida",
                errorMsg,
                request.getRequestURI());
        return new ResponseEntity<>(exceptionsDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ExceptionsDto> handleProductNotFoundException(ProductNotFoundException ex, HttpServletRequest request) {
        ExceptionsDto exceptionsDto = new ExceptionsDto(
                System.currentTimeMillis(),
                HttpStatus.NOT_FOUND.value(),
                "Não encontrado",
                ex.getMessage(),
                request.getRequestURI());
        return new ResponseEntity<>(exceptionsDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ProductAlreadyExistsException.class)
    public ResponseEntity<ExceptionsDto> handleProductAlreadyExistsException(ProductAlreadyExistsException ex, HttpServletRequest request) {
        ExceptionsDto exceptionsDto = new ExceptionsDto(
                System.currentTimeMillis(),
                HttpStatus.BAD_REQUEST.value(),
                "Requisição inválida",
                ex.getMessage(),
                request.getRequestURI());
        return new ResponseEntity<>(exceptionsDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ProductInsertException.class)
    public ResponseEntity<ExceptionsDto> handleProductInsertException(ProductInsertException ex, HttpServletRequest request) {
        ExceptionsDto exceptionsDto = new ExceptionsDto(
                System.currentTimeMillis(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Erro interno no servidor",
                ex.getMessage(),
                request.getRequestURI());
        return new ResponseEntity<>(exceptionsDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ProductUpdateException.class)
    public ResponseEntity<ExceptionsDto> handleProductUpdateException(ProductUpdateException ex, HttpServletRequest request) {
        ExceptionsDto exceptionsDto = new ExceptionsDto(
                System.currentTimeMillis(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Erro interno no servidor",
                ex.getMessage(),
                request.getRequestURI());
        return new ResponseEntity<>(exceptionsDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
