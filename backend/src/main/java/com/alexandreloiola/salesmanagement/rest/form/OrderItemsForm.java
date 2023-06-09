package com.alexandreloiola.salesmanagement.rest.form;

import javax.validation.constraints.*;
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
