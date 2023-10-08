package com.alexandreloiola.salesmanagement.service.exceptions.employee;

public class EmployeeUpdateException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public EmployeeUpdateException(String msg) {
        super(msg);
    }

    public EmployeeUpdateException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
