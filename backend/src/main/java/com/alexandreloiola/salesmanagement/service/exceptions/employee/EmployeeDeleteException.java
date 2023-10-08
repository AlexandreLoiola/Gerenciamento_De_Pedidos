package com.alexandreloiola.salesmanagement.service.exceptions.employee;

public class EmployeeDeleteException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public EmployeeDeleteException(String msg) {
        super(msg);
    }

    public EmployeeDeleteException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
