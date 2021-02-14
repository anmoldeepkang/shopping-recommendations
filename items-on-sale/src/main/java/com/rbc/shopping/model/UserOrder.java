package com.rbc.shopping.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * @author Anmoldeep Singh Kang
 * This class represents an Order of the User.
 */
@Entity
public class UserOrder {
	@Id
	@Column(name = "order_id")
	private String orderId;

	@ManyToOne
	@JoinColumn(name = "user_id")
	@JsonBackReference
	private User user;

	@OneToMany(mappedBy = "order")
	private List<OrderLine> orderLines;

	@Column(name = "order_time")
	private Timestamp orderTime;

	/**
	 * @return User.
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return Order lines in the order.
	 */
	public List<OrderLine> getOrderLines() {
		return orderLines;
	}

	/**
	 * @param Order lines in the order.
	 */
	public void setOrderLines(List<OrderLine> orderLines) {
		this.orderLines = orderLines;
	}

	/**
	 * @return orderId
	 */
	public String getOrderId() {
		return orderId;
	}

	/**
	 * @param orderId
	 */
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	/**
	 * @return orderTime.
	 */
	public Timestamp getOrderTime() {
		return orderTime;
	}

	/**
	 * @param orderTime
	 */
	public void setOrderTime(Timestamp orderTime) {
		this.orderTime = orderTime;
	}

}
