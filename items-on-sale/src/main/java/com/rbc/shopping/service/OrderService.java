package com.rbc.shopping.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rbc.shopping.model.UserOrder;
import com.rbc.shopping.repository.OrderRepository;
/**
 * @author Anmoldeep Singh Kang
 * This class implements the Service layer for Order.
 *
 */
@Service    
public class OrderService {
	
	@Autowired    
	private OrderRepository orderRepository;    
	
	/**
	 * @return List of all orders.
	 */
	public List<UserOrder> getAllOrders()  {    
		List<UserOrder> orders = orderRepository.findAll();
		return orders;    
	}
	
	/**
	 * @param order
	 */
	public void addOrder(UserOrder order)  {    
		orderRepository.save(order);    
	}
	
	/**
	 * @param orderId
	 * @return User's order.
	 */
	public UserOrder getOrderById(String orderId) {
		return orderRepository.findById(orderId).get();
	}
	
}
