package com.alexandreloiola.salesmanagement.service.exceptions.employeePosition;

public class EmployeePositionNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public EmployeePositionNotFoundException(String msg) {
        super(msg);
    }

    public EmployeePositionNotFoundException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
