package com.rbc.shopping.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * @author Anmoldeep Singh Kang
 * The class represents an OrderLine for an Order. An order can have multiple order lines.
 */
@Entity
public class OrderLine {

	@Id
	@Column(name = "order_line_id")
	private String orderLineId;

	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;

	@ManyToOne
	@JoinColumn(name = "order_id")
	private UserOrder order;
	
	@Column(name = "rating")
	private Integer rating;

	/**
	 * @return Order corresponding to the Order Line
	 */
	public UserOrder getOrder() {
		return order;
	}

	/**
	 * @param Order corresponding to the orderline
	 */
	public void setOrder(UserOrder order) {
		this.order = order;
	}

	/**
	 * @return Product purchased in Order Line
	 */
	public Product getProduct() {
		return product;
	}

	/**
	 * @param Product purchased in order line
	 */
	public void setProduct(Product product) {
		this.product = product;
	}


	/**
	 * @return orderLineId
	 */
	public String getOrderLineId() {
		return orderLineId;
	}

	/**
	 * @param orderLineId
	 */
	public void setOrderLineId(String orderLineId) {
		this.orderLineId = orderLineId;
	}

	/**
	 * @return rating for the item purchased
	 */
	public Integer getRating() {
		return rating;
	}

	/**
	 * @param rating for the item purchased
	 */
	public void setRating(Integer rating) {
		this.rating = rating;
	}

}
