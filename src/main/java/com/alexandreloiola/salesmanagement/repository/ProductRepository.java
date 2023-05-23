package com.alexandreloiola.salesmanagement.repository;

import com.alexandreloiola.salesmanagement.model.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductModel, Long> {
}
