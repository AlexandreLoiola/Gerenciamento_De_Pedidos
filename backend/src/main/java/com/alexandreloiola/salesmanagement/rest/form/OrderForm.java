package com.alexandreloiola.salesmanagement.rest.form;

import javax.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderForm {

    @NotNull(message = "Insira o status do produto")
    private String status;

    @NotNull(message = "Insira o identificador do cliente")
    private String customer;

    @NotNull(message = "Insira o identificador do vendedor")
    private String employee;

}
