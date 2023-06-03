package com.alexandreloiola.salesmanagement.repository;

import com.alexandreloiola.salesmanagement.model.OrderModel;
import com.alexandreloiola.salesmanagement.model.SellerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<OrderModel, Long> {
    Optional<OrderModel> findByOrderNumber(Long orderNumber);

}
