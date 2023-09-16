package com.alexandreloiola.salesmanagement.service.exceptions.orderStatus;

public class OrderStatusNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public OrderStatusNotFoundException(String msg) {
        super(msg);
    }

    public OrderStatusNotFoundException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
