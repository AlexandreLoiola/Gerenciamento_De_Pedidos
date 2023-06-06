package com.alexandreloiola.salesmanagement.rest.form;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderUpdateForm {

    @NotNull(message = "Insira o status do produto")
    private String status;

}
