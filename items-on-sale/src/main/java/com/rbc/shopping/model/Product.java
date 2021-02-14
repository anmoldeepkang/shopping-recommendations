package com.rbc.shopping.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * @author Anmoldeep Singh Kang
 * This class represents the products/items which are on sale or which have been purchased in earlier orders.
 */
@Entity
public class Product {

	@Id
	@Column(name = "product_id")
	private String productId;

	@Column(name = "name")
	private String name;

	@Column(name = "category")
	private String category;

	@OneToMany(mappedBy = "product")
	@JsonBackReference
	private List<OrderLine> orderLine;

	@OneToMany(mappedBy = "product")
	@JsonBackReference
	private List<Wishlist> wishedBy;

	/**
	 * @return The Wishlists which contain the product.
	 */
	public List<Wishlist> getWishedBy() {
		return wishedBy;
	}

	/**
	 * @param wishedBy
	 */
	public void setWishedBy(List<Wishlist> wishedBy) {
		this.wishedBy = wishedBy;
	}

	/**
	 * @return List of order lines which contain the product.
	 */
	public List<OrderLine> getOrderLine() {
		return this.orderLine;
	}

	/**
	 * @param orderLine
	 */
	public void setOrderLine(List<OrderLine> orderLine) {
		this.orderLine = orderLine;
	}

	/**
	 * @return category of the product
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * @param category of the product
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * @return productId.
	 */
	public String getProductId() {
		return productId;
	}

	/**
	 * @param productId
	 */
	public void setProductId(String productId) {
		this.productId = productId;
	}

	/**
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

}
