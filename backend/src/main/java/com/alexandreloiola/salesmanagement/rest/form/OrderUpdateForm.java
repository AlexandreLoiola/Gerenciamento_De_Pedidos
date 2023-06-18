package com.alexandreloiola.salesmanagement.rest.form;

import javax.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderUpdateForm {

    @NotNull(message = "Insira o status do produto")
    private String status;

}
