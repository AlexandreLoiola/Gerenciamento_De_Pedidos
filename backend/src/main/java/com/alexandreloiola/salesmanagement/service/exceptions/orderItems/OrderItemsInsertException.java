package com.alexandreloiola.salesmanagement.service.exceptions.orderItems;

public class OrderItemsInsertException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public OrderItemsInsertException(String msg) {
        super(msg);
    }

    public OrderItemsInsertException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
