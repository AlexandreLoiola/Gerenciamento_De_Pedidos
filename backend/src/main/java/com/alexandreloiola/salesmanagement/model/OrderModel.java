package com.alexandreloiola.salesmanagement.model;

import javax.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "TB_ORDER")
public class OrderModel {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code_order", nullable = false, unique = true)
    private Long orderNumber;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "data_time", nullable = false)
    private LocalDateTime dataTime;

    @Column(name = "status", nullable = false)
    private String status;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private CustomerModel customer;

    @ManyToOne
    @JoinColumn(name = "seller_id", referencedColumnName = "id")
    private SellerModel seller;

}
