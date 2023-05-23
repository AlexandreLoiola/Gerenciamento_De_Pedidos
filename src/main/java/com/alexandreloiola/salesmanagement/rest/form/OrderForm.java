package com.alexandreloiola.salesmanagement.rest.form;

import com.alexandreloiola.salesmanagement.model.SellerModel;
import com.alexandreloiola.salesmanagement.rest.dto.CustomerDto;
import com.alexandreloiola.salesmanagement.rest.dto.SellerDto;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderForm {

    @NotNull(message = "Insira o preço do pedido")
    @DecimalMin(value = "0.00", message = "O preço do produto não pode ser negativo")
    private BigDecimal price;

    @NotNull(message = "Insira o status do produto")
    private String status;

    @NotNull(message = "Insira o identificador do cliente")
    private CustomerDto customer;

    @NotNull(message = "Insira o identificador do vendedor")
    private SellerDto seller;

}
