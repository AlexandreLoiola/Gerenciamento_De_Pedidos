package com.alexandreloiola.salesmanagement.rest.form;

import javax.validation.constraints.*;
import lombok.Data;

@Data
public class OrderItemsForm {

    @NotNull(message = "A quantidade não pode ser nula")
    @Min(value = 1, message = "A quantidade deve ser pelo menos 1")
    @Max(value = 99999, message = "A quantidade não pode exceder 99999")
    private int quantity;

    @NotNull(message = "A ordem de pedido não pode ser nula")
    private Long orderNumber;

    @NotEmpty(message = "O campo nome não pode ser vazio")
    @NotBlank(message = "O campo nome não pode ficar em branco.")
    @Size(max = 100)
    private String productName;

}
