package com.alexandreloiola.salesmanagement.service.exceptions.orderStatus;

public class OrderStatusAlreadyExistsException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public OrderStatusAlreadyExistsException(String msg) {
        super(msg);
    }

    public OrderStatusAlreadyExistsException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
