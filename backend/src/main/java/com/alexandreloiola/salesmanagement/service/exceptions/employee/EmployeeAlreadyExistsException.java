package com.alexandreloiola.salesmanagement.service.exceptions.employee;

public class EmployeeAlreadyExistsException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public EmployeeAlreadyExistsException(String msg) {
        super(msg);
    }

    public EmployeeAlreadyExistsException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
