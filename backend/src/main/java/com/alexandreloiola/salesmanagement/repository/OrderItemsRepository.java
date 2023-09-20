package com.alexandreloiola.salesmanagement.repository;

import com.alexandreloiola.salesmanagement.model.CustomerModel;
import com.alexandreloiola.salesmanagement.model.OrderItemsModel;
import com.alexandreloiola.salesmanagement.model.OrderModel;
import com.alexandreloiola.salesmanagement.model.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderItemsRepository extends JpaRepository<OrderItemsModel, Long> {

    @Query(value = "SELECT * FROM TB_ORDER_ITEMS WHERE ID_ORDER=:orderId AND ID_PRODUCT=:productId", nativeQuery = true)
    Optional<OrderItemsModel> findByOrderIdAndProductId(@Param("orderId") long orderId, @Param("productId") long productId);

    @Query(value = "SELECT * FROM TB_ORDER_ITEMS WHERE ID_ORDER=:orderId", nativeQuery = true)
    Optional<List<OrderItemsModel>> findAllByOrderId(@Param("orderId") long orderId);


    Optional<OrderItemsModel> findByOrder(OrderModel orderModel);

    Optional<OrderItemsModel> findByProduct(ProductModel productModel);

}