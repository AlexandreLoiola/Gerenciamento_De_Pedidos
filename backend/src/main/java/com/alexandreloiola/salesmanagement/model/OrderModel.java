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

    @Column(name = "order_number", length = 10, nullable = false, unique = true)
    private Long orderNumber;

    @Column(name = "data_time", nullable = false)
    private LocalDateTime dataTime;

    @Column(name = "total_price", precision = 10, scale = 2 , nullable = false)
    private BigDecimal totalPrice;

    @OneToOne
    @JoinColumn(name = "id_status", referencedColumnName = "id")
    private OrderStatusModel idStatus;

    @ManyToOne
    @JoinColumn(name = "id_customer", referencedColumnName = "id")
    private PersonModel idCustomer;

    @ManyToOne
    @JoinColumn(name = "id_employee", referencedColumnName = "id")
    private PersonModel idSeller;

}