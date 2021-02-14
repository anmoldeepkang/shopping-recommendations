package com.rbc.shopping.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rbc.shopping.model.Product;
import com.rbc.shopping.repository.ProductRepository;
/**
 * @author Anmoldeep Singh Kang
 * This class implements the Service layer for Product.
 *
 */
@Service
public class ProductService {
	@Autowired    
	private ProductRepository productRepository;    
	
	/**
	 * @return Products.
	 */
	public List<Product> getAllProducts()  {    
		List<Product> Products = productRepository.findAll();
		return Products;    
	}
	
	/**
	 * @param Product
	 */
	public void addProduct(Product Product)  {    
		productRepository.save(Product);    
	}
	
	/**
	 * @param productId
	 * @return Product
	 */
	public Product getProductById(String productId) {
		return productRepository.findById(productId).get();
	}
}
