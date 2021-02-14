package com.rbc.shopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rbc.shopping.model.UserOrder;

/**
 * @author Anmoldeep Singh Kang
 * Repository class for Order
 */
@Repository
public interface OrderRepository extends JpaRepository<UserOrder, String>{

}
