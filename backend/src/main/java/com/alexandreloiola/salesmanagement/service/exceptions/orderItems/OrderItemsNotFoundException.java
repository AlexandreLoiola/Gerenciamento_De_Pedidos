package com.alexandreloiola.salesmanagement.service.exceptions.orderItems;

public class OrderItemsNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public OrderItemsNotFoundException(String msg) {
        super(msg);
    }

    public OrderItemsNotFoundException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
