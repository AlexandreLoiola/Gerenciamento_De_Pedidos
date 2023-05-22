package com.alexandreloiola.salesmanagement.repository;

import com.alexandreloiola.salesmanagement.model.CustomerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerModel, Long> {
}
