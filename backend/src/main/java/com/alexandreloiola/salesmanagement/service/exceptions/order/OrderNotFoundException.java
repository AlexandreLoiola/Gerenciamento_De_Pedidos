package com.alexandreloiola.salesmanagement.service.exceptions.order;

public class OrderNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public OrderNotFoundException(String msg) {
        super(msg);
    }

    public OrderNotFoundException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
