package com.rbc.shopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rbc.shopping.model.Wishlist;

/**
 * @author Anmoldeep Singh Kang
 * Repository class for Wish list.
 */
public interface WishRepository extends JpaRepository<Wishlist, String> {

}
