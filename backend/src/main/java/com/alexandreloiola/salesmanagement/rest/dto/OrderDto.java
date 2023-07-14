package com.alexandreloiola.salesmanagement.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {

    private Long orderNumber;

    private BigDecimal price;

    private LocalDateTime dateTime;

    private String status;

    private String customer;

    private String seller;

}
