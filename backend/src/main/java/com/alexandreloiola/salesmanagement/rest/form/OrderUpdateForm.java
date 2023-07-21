package com.alexandreloiola.salesmanagement.rest.form;

import javax.validation.constraints.*;

import com.alexandreloiola.salesmanagement.model.OrderStatusModel;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderUpdateForm {

    @NotNull(message = "Insira o status do pedido")
    private Long idStatus;

}
