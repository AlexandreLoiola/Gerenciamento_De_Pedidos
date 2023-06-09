package com.alexandreloiola.salesmanagement.rest.form;

import javax.validation.constraints.*;
import lombok.Data;

@Data
public class OrderItemsUpdateForm {

    @NotNull
    private int quantity;

}
