package com.alexandreloiola.salesmanagement.rest.controller.exceptions;

import javax.servlet.http.HttpServletRequest;

import com.alexandreloiola.salesmanagement.service.exceptions.order.OrderInsertException;
import com.alexandreloiola.salesmanagement.service.exceptions.order.OrderNotFoundException;
import com.alexandreloiola.salesmanagement.service.exceptions.order.OrderPriceUpdateException;
import com.alexandreloiola.salesmanagement.service.exceptions.order.OrderUpdateException;
import com.alexandreloiola.salesmanagement.service.exceptions.orderStatus.OrderStatusAlreadyExistsException;
import com.alexandreloiola.salesmanagement.service.exceptions.orderStatus.OrderStatusInsertException;
import com.alexandreloiola.salesmanagement.service.exceptions.orderStatus.OrderStatusNotFoundException;
import com.alexandreloiola.salesmanagement.service.exceptions.orderStatus.OrderStatusUpdateException;
import com.alexandreloiola.salesmanagement.service.exceptions.product.ProductAlreadyExistsException;
import com.alexandreloiola.salesmanagement.service.exceptions.product.ProductInsertException;
import com.alexandreloiola.salesmanagement.service.exceptions.product.ProductNotFoundException;
import com.alexandreloiola.salesmanagement.service.exceptions.product.ProductUpdateException;
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

    @ExceptionHandler(OrderStatusNotFoundException.class)
    public ResponseEntity<ExceptionsDto> handleOrderStatusNotFoundException(OrderStatusNotFoundException ex, HttpServletRequest request) {
        ExceptionsDto exceptionsDto = new ExceptionsDto(
                System.currentTimeMillis(),
                HttpStatus.NOT_FOUND.value(),
                "Não encontrado",
                ex.getMessage(),
                request.getRequestURI());
        return new ResponseEntity<>(exceptionsDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(OrderStatusAlreadyExistsException.class)
    public ResponseEntity<ExceptionsDto> handleOrderStatusAlreadyExistsException(OrderStatusAlreadyExistsException ex, HttpServletRequest request) {
        ExceptionsDto exceptionsDto = new ExceptionsDto(
                System.currentTimeMillis(),
                HttpStatus.CONFLICT.value(),
                "Requisição inválida",
                ex.getMessage(),
                request.getRequestURI());
        return new ResponseEntity<>(exceptionsDto, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(OrderStatusInsertException.class)
    public ResponseEntity<ExceptionsDto> handleOrderStatusInsertException(OrderStatusInsertException ex, HttpServletRequest request) {
        ExceptionsDto exceptionsDto = new ExceptionsDto(
                System.currentTimeMillis(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Requisição inválida",
                ex.getMessage(),
                request.getRequestURI());
        return new ResponseEntity<>(exceptionsDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(OrderStatusUpdateException.class)
    public ResponseEntity<ExceptionsDto> handleOrderStatusUpdateException(OrderStatusUpdateException ex, HttpServletRequest request) {
        ExceptionsDto exceptionsDto = new ExceptionsDto(
                System.currentTimeMillis(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Requisição inválida",
                ex.getMessage(),
                request.getRequestURI());
        return new ResponseEntity<>(exceptionsDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(OrderInsertException.class)
    public ResponseEntity<ExceptionsDto> handleOrderInsertException(OrderInsertException ex, HttpServletRequest request) {
        ExceptionsDto exceptionsDto = new ExceptionsDto(
                System.currentTimeMillis(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Requisição inválida",
                ex.getMessage(),
                request.getRequestURI());
        return new ResponseEntity<>(exceptionsDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<ExceptionsDto> handleOrderNotFoundException(OrderNotFoundException ex, HttpServletRequest request) {
        ExceptionsDto exceptionsDto = new ExceptionsDto(
                System.currentTimeMillis(),
                HttpStatus.NOT_FOUND.value(),
                "Não encontrado",
                ex.getMessage(),
                request.getRequestURI());
        return new ResponseEntity<>(exceptionsDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(OrderPriceUpdateException.class)
    public ResponseEntity<ExceptionsDto> handleOrderPriceUpdateException(OrderPriceUpdateException ex, HttpServletRequest request) {
        ExceptionsDto exceptionsDto = new ExceptionsDto(
                System.currentTimeMillis(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Requisição inválida",
                ex.getMessage(),
                request.getRequestURI());
        return new ResponseEntity<>(exceptionsDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(OrderUpdateException.class)
    public ResponseEntity<ExceptionsDto> handleOrderUpdateException(OrderUpdateException ex, HttpServletRequest request) {
        ExceptionsDto exceptionsDto = new ExceptionsDto(
                System.currentTimeMillis(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Requisição inválida",
                ex.getMessage(),
                request.getRequestURI());
        return new ResponseEntity<>(exceptionsDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
