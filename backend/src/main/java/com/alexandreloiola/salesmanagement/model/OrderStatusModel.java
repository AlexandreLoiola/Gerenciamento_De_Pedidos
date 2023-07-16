package com.alexandreloiola.salesmanagement.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "TB_ORDER_STATUS")
public class OrderStatusModel {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description", length = 100, nullable = false, unique = true)
    private String description;

    @Column(name = "isActive", nullable = false)
    private Boolean isActive;

}
