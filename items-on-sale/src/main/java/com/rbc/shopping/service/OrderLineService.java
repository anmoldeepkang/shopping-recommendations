package com.rbc.shopping.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rbc.shopping.model.OrderLine;
import com.rbc.shopping.repository.OrderLineRepository;

/**
 * @author Anmoldeep Singh Kang
 * This class implements the Service layer for Order Line.
 *
 */
@Service    
public class OrderLineService {
	
	@Autowired    
	private OrderLineRepository orderLineRepository;    
	
	/** 
	 * @return All order lines.
	 */
	public List<OrderLine> getAllOrderLines()  {    
		List<OrderLine> orderLines = (List<OrderLine>) orderLineRepository.findAll();
		return orderLines;    
	}
	
	/**
	 * @param OrderLine
	 */
	public void addOrderLine(OrderLine OrderLine)  {    
		orderLineRepository.save(OrderLine);    
	}
	
	/**
	 * @param orderLineId
	 * @return Order Line.
	 */
	public OrderLine getOrderLineById(String orderLineId) {
		return orderLineRepository.findById(orderLineId).get();
	}
	
}
