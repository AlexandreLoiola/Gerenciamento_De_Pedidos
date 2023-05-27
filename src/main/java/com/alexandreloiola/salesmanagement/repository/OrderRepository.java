package com.alexandreloiola.salesmanagement.repository;

import com.alexandreloiola.salesmanagement.model.OrderModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<OrderModel, Long> {

}
