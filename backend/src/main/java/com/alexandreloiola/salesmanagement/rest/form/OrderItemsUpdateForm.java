package com.alexandreloiola.salesmanagement.rest.form;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrderItemsUpdateForm {

    @NotNull
    private int quantity;

}
