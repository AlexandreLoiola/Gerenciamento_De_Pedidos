package com.alexandreloiola.salesmanagement.rest.form;

import javax.validation.constraints.*;
import lombok.Data;

@Data
public class OrderItemsUpdateForm {

    @NotNull(message = "A quantidade não pode ser nula")
    @Min(value = 1, message = "A quantidade deve ser pelo menos 1")
    @Max(value = 99999, message = "A quantidade não pode exceder 99999")
    private int quantity;

}