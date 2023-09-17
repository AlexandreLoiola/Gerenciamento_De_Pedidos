package com.alexandreloiola.salesmanagement.service.exceptions.order;

public class OrderUpdateException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public OrderUpdateException(String msg) {
        super(msg);
    }

    public OrderUpdateException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
