package com.alexandreloiola.salesmanagement.service.exceptions.order;

public class NotValidSellerException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public NotValidSellerException(String msg) {
        super(msg);
    }

    public NotValidSellerException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
