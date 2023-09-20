package com.alexandreloiola.salesmanagement.service.exceptions.order;

public class NotValidCustomerException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public NotValidCustomerException(String msg) {
        super(msg);
    }

    public NotValidCustomerException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
