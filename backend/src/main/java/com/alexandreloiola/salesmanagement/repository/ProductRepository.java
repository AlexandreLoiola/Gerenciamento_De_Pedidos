package com.alexandreloiola.salesmanagement.repository;

import com.alexandreloiola.salesmanagement.model.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductModel, Long> {
    Optional<ProductModel> findByName(String name);
}
