package com.alexandreloiola.salesmanagement.service.exceptions;

public class ProductUpdateException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ProductUpdateException(String msg) {
        super(msg);
    }

    public ProductUpdateException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
