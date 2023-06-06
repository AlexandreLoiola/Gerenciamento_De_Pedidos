package com.alexandreloiola.salesmanagement.repository;

import com.alexandreloiola.salesmanagement.model.OrderItemsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemsRepository extends JpaRepository<OrderItemsModel, Long> {
}
