package com.alexandreloiola.salesmanagement.repository;

import com.alexandreloiola.salesmanagement.model.AuthorizationModel;
import com.alexandreloiola.salesmanagement.model.OrderStatusModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorizationRepository extends JpaRepository<AuthorizationModel, Long> {
    Optional<AuthorizationModel> findByDescription(String description);
}
