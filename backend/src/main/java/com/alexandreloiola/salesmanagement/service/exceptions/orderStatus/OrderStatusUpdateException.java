package com.alexandreloiola.salesmanagement.service.exceptions.orderStatus;

public class OrderStatusUpdateException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public OrderStatusUpdateException(String msg) {
        super(msg);
    }

    public OrderStatusUpdateException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
