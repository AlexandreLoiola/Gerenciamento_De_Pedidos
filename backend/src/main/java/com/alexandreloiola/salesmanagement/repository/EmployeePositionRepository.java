package com.alexandreloiola.salesmanagement.repository;

import com.alexandreloiola.salesmanagement.model.AuthorizationModel;
import com.alexandreloiola.salesmanagement.model.EmployeeModel;
import com.alexandreloiola.salesmanagement.model.EmployeePositionModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeePositionRepository extends JpaRepository<EmployeePositionModel, Long> {
    Optional<EmployeePositionModel> findByDescription(String description);

}
