package com.alexandreloiola.salesmanagement.service.exceptions.orderStatus;

public class OrderStatusInsertException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public OrderStatusInsertException(String msg) {
        super(msg);
    }

    public OrderStatusInsertException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
