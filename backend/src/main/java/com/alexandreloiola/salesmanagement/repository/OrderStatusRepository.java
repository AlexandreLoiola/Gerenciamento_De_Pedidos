package com.alexandreloiola.salesmanagement.repository;

import com.alexandreloiola.salesmanagement.model.OrderStatusModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderStatusRepository extends JpaRepository<OrderStatusModel, Long> {
    Optional<OrderStatusModel> findByDescription(String description);
}
