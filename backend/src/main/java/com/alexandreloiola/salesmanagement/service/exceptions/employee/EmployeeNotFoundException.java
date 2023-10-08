package com.alexandreloiola.salesmanagement.service.exceptions.employee;

public class EmployeeNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public EmployeeNotFoundException(String msg) {
        super(msg);
    }

    public EmployeeNotFoundException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
