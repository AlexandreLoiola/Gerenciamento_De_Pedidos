package com.alexandreloiola.salesmanagement.service.exceptions.order;

public class OrderPriceUpdateException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public OrderPriceUpdateException(String msg) {
        super(msg);
    }

    public OrderPriceUpdateException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
