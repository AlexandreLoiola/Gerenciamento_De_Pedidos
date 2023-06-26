package com.alexandreloiola.salesmanagement.model;

import javax.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
@Table(name = "TB_PRODUCT")
public class ProductModel {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 128, nullable = false, unique = true)
    private String name;

    @Column(name = "description", length = 256, nullable = false)
    private String description;

    @Column(name = "precoUnitario", nullable = false, precision = 18, scale = 2)
    private BigDecimal unitPrice;

    @Column(name = "isActive", nullable = false)
    private Boolean isActive;
}
