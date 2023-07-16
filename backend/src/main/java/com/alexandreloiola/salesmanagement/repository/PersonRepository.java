package com.alexandreloiola.salesmanagement.repository;

import com.alexandreloiola.salesmanagement.model.CustomerModel;
import com.alexandreloiola.salesmanagement.model.PersonModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<PersonModel, Long> {
    Optional<CustomerModel> findByName(String name);
}
