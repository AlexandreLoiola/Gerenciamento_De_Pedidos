package com.alexandreloiola.salesmanagement.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    private String name;

    private String description;

    private BigDecimal unitPrice;

    private Boolean isActive;
}
