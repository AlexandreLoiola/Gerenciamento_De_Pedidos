package com.alexandreloiola.salesmanagement.repository;

import com.alexandreloiola.salesmanagement.model.EmployeeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeModel, Long> {

}
