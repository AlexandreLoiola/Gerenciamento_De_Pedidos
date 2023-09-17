package com.alexandreloiola.salesmanagement.rest.form;

import javax.validation.constraints.*;

import com.alexandreloiola.salesmanagement.model.OrderStatusModel;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderUpdateForm {

    @NotBlank(message = "O campo status não pode ficar em branco")
    @Size(min = 5, max = 100, message = "O status deve ter entre 5 e 100 caracteres")
    private String status;

}
