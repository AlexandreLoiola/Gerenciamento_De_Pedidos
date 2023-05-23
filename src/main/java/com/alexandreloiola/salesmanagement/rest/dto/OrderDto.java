package com.alexandreloiola.salesmanagement.rest.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class OrderDto {

    private Long orderNumber;
    private BigDecimal price;
    private LocalDateTime dateTime;
    private String status;
    private CustomerDto customer;
    private SellerDto seller;

}
