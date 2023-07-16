package com.alexandreloiola.salesmanagement.repository;

import com.alexandreloiola.salesmanagement.model.OrderStatusModel;
import com.alexandreloiola.salesmanagement.model.RoleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<RoleModel, Long> {
    Optional<RoleModel> findByDescription(String description);
}
