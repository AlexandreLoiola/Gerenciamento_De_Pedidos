package com.alexandreloiola.salesmanagement.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.management.DescriptorKey;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderStatusDto {
    private String description;
    private Boolean isActive;
}
