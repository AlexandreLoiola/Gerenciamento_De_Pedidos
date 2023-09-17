package com.alexandreloiola.salesmanagement.service.exceptions.order;

public class OrderInsertException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public OrderInsertException(String msg) {
        super(msg);
    }

    public OrderInsertException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
