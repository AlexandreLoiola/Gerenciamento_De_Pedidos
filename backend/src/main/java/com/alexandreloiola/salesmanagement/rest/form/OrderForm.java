package com.alexandreloiola.salesmanagement.rest.form;

import javax.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderForm {

    @NotBlank(message = "O campo cpf do cliente não pode ficar em branco")
    @Size(min = 11, max = 11, message = "O cpf deve ter 11 caracteres")
    private String cpfCustomer;

    @NotBlank(message = "O campo cpf do vendedor não pode ficar em branco")
    @Size(min = 11, max = 11, message = "O cpf deve ter 11 caracteres")
    private String cpfSeller;

}
