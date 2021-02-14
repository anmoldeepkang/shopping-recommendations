package com.rbc.shopping.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * @author Anmoldeep Singh Kang
 * This class represents wishlist of the user. 
 */
@Entity
public class Wishlist {

	@Id
	private String id;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;

	/**
	 * @return id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return User to which the wish belongs to.
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param User
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return Product
	 */
	public Product getProduct() {
		return product;
	}

	/**
	 * @param Product
	 */
	public void setProduct(Product product) {
		this.product = product;
	}

}
