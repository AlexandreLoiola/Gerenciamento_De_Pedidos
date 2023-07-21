package com.alexandreloiola.salesmanagement.rest.form;

import javax.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderForm {

    @NotNull(message = "Insira o cpf do cliente")
    private String cpfCustomer;

    @NotNull(message = "Insira o cpf do vendedor")
    private String cpfSeller;

}
