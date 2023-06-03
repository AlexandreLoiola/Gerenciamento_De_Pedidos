package com.alexandreloiola.salesmanagement.rest.form;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrderItemsForm {

    @NotNull
    private int quantity;

    @NotNull
    private Long orderNumber;

    @NotNull
    private String productName;

}
