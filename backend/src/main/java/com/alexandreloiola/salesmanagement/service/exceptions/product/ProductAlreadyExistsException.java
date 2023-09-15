package com.alexandreloiola.salesmanagement.service.exceptions.product;

public class ProductAlreadyExistsException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public ProductAlreadyExistsException(String msg) {
        super(msg);
    }

    public ProductAlreadyExistsException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
