package com.rbc.shopping.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * @author Anmoldeep Singh Kang
 * This class represents the user whi is registered and has access to recommendations.
 */
@Entity
public class User {

	@Id
	@Column(name = "user_id")
	private String userId;

	@Column(name = "name")
	private String name;

	@Column(name = "username")
	private String username;

	@OneToMany(mappedBy = "user")
	private List<UserOrder> order;

	@OneToMany(mappedBy = "user")
	@JsonBackReference
	private List<Wishlist> wishes;

	@Column(name = "password")
	private String password;

	/**
	 * @return Wish list of User.
	 */
	public List<Wishlist> getWishes() {
		return wishes;
	}

	/**
	 * @param Wishlist of User.
	 */
	public void setWishes(List<Wishlist> wishes) {
		this.wishes = wishes;
	}

	/**
	 * @return Orders of the User.
	 */
	public List<UserOrder> getUserOrder() {
		return order;
	}

	/**
	 * @param Orders of the User.
	 */
	public void setUserOrder(List<UserOrder> order) {
		this.order = order;
	}

	/**
	 * @return userId.
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 */
	public void setUserId(String userId) {
		this.userId = userId;
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

	/**
	 * @return username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
}
