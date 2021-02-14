package com.rbc.shopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rbc.shopping.model.Product;

/**
 * @author Anmoldeep Singh Kang
 * Repository class for Product.
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, String>{

}
