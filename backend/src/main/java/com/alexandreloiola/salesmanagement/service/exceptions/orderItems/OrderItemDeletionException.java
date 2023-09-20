package com.alexandreloiola.salesmanagement.service.exceptions.orderItems;

public class OrderItemDeletionException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public OrderItemDeletionException(String msg) {
        super(msg);
    }

    public OrderItemDeletionException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
