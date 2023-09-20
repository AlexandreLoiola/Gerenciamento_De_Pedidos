package com.alexandreloiola.salesmanagement.service.exceptions.orderItems;

public class OrderItemsUpdateException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public OrderItemsUpdateException(String msg) {
        super(msg);
    }

    public OrderItemsUpdateException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
