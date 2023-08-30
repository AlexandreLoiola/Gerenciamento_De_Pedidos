package com.alexandreloiola.salesmanagement.model;

import javax.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "TB_ORDER_ITEMS")
public class OrderItemsModel {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "id_order", referencedColumnName = "id")
    private OrderModel order;

    @ManyToOne
    @JoinColumn(name = "id_product", referencedColumnName = "id")
    private ProductModel product;

}
