package com.alexandreloiola.salesmanagement.rest.dto;

import lombok.Data;

@Data
public class OrderItemsDto {

    private int quantity;

    private Long orderNumber;

    private String productName;

}
