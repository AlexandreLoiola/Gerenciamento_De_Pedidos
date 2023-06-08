package com.alexandreloiola.salesmanagement.repository;

import com.alexandreloiola.salesmanagement.model.CustomerModel;
import com.alexandreloiola.salesmanagement.model.SellerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerModel, Long> {
    Optional<CustomerModel> findByName(String name);

    Optional<CustomerModel> findByEmail(String email);

    Optional<CustomerModel> findByCpf(String cpf);
}
