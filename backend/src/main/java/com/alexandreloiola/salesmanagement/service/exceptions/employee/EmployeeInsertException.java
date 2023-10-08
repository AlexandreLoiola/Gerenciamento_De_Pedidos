package com.alexandreloiola.salesmanagement.service.exceptions.employee;

public class EmployeeInsertException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public EmployeeInsertException(String msg) {
        super(msg);
    }

    public EmployeeInsertException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
