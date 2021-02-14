package com.rbc.shopping.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rbc.shopping.model.OrderLine;

/**
 * @author Anmoldeep Singh Kang
 * Repository class for Order Line.
 */
@Repository
public interface OrderLineRepository extends CrudRepository<OrderLine, String>{

}
