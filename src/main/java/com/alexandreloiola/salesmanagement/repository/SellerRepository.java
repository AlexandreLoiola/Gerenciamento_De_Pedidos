package com.alexandreloiola.salesmanagement.repository;

import com.alexandreloiola.salesmanagement.model.SellerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerRepository extends JpaRepository<SellerModel, Long> {
}
