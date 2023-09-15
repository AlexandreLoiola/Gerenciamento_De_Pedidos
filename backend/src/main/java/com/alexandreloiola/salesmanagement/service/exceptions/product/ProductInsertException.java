package com.alexandreloiola.salesmanagement.service.exceptions.product;

public class ProductInsertException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ProductInsertException(String msg) {
        super(msg);
    }

    public ProductInsertException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
