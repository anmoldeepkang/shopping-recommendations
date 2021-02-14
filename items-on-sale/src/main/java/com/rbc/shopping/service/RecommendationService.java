package com.rbc.shopping.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rbc.shopping.model.OrderLine;
import com.rbc.shopping.model.Product;
import com.rbc.shopping.model.Wishlist;
import com.rbc.shopping.util.DateUtil;

/**
 * @author Anmoldeep Singh Kang This class implements the Service layer for
 *         recommendations.
 *
 */
@Service
public class RecommendationService {

	@Autowired
	UserService userService;

	@Autowired
	ProductService productService;

	@Autowired
	OrderLineService orderLineService;

	/*
	 * Since we know that the items on sale list changes only once daily, we cache
	 * the results of recommendations (for improved performance) and also maintain
	 * the timestamp of the last request for recommendations by the user. Ideally,
	 * these should be Expiring hashmaps so that entries are invalidated after
	 * certain time and cache doesn't grow indefinitely.
	 */
	Map<String, Map<String, List<Product>>> cachedRecommendations = new HashMap<>();
	Map<String, Date> userRecommendationRequestLog = new HashMap<>();

	/**
	 * @param userName
	 * @return A Map consisting of products under hot deals, wish list & past orders
	 */
	public Map<String, List<Product>> getItemsOnSale(String userName) {
		// If the last request made to get recommendations was on the current date, then
		// return the
		// result directly from the cache.
		if (userRecommendationRequestLog.containsKey(userName)
				&& userRecommendationRequestLog.get(userName).equals(DateUtil.getCurrentDate()))
			return cachedRecommendations.get(userName);
		Map<String, List<Product>> recommendations = new HashMap<>();
		// Get recommendations based on past orders of the user.
		recommendations.put("pastOrders", getProductsFromPastOrders(userName));
		// Get hot deals based on top ratings by other users.
		recommendations.put("hotDeals", getTopRatedProductsByOtherUsers(userName));
		// Get products based on wish list of users.
		recommendations.put("wishList", getProductsInUsersWishList(userName));
		// Add the results to the Cache.
		cachedRecommendations.put(userName, recommendations);
		userRecommendationRequestLog.put(userName, DateUtil.getCurrentDate());
		return recommendations;
	}

	/**
	 * @param username
	 * @return List of products based on top rated categories in the past orders.
	 */
	public List<Product> getTopRatedProductsByOtherUsers(String username) {
		// Filter the order lines which are from other users.
		List<OrderLine> orderLines = orderLineService.getAllOrderLines().stream()
				.filter(o -> !o.getOrder().getUser().getUsername().equals(username)).collect(Collectors.toList());
		// Remove order lines if ratings are null.
		orderLines.removeIf(e -> (e.getRating() == null));
		List<Product> topRatedProducts = new ArrayList<>();
		// Get recommendations for order lines.
		topRatedProducts.addAll(getTopRatedProductsFromOrderLines(orderLines));
		return topRatedProducts;
	}

	/**
	 * @param username
	 * @return List of Products under hot deals.
	 */
	private List<Product> getProductsFromPastOrders(String username) {
		// Filter to get the order lines of the current user.
		List<OrderLine> orderLinesOfUser = orderLineService.getAllOrderLines().stream()
				.filter(o -> o.getOrder().getUser().getUsername().equals(username)).collect(Collectors.toList());
		// Remove order lines with ratings as null.
		orderLinesOfUser.removeIf(e -> (e.getRating() == null));
		List<Product> topRatedProducts = new ArrayList<>();
		// Get top rated products from order lines of other users.
		topRatedProducts.addAll(getTopRatedProductsFromOrderLines(orderLinesOfUser));
		return topRatedProducts;

	}

	/**
	 * @param username
	 * @return List of Products under Wish list.
	 */
	public List<Product> getProductsInUsersWishList(String username) {
		List<Wishlist> wishlist = userService.getUserByUsername(username).getWishes();
		List<Product> productsInWishList = new ArrayList<Product>();
		for (Wishlist wish : wishlist) {
			productsInWishList.add(wish.getProduct());
		}
		return productsInWishList;
	}

	/**
	 * @param orderLines
	 * @return List of products based on top rated categories in order lines.
	 */
	/*
	 * Logic: Maintain two maps: one containing category and the aggregate sum of
	 * the ratings from order lines. another containing the category and the number
	 * of times the category is present in order lines. Division of the value in
	 * map1 with corresponding value in map2 will give average rating of the
	 * category. Get list of all the items on sale. Sort the list of items on sale
	 * based on the average rating of category obtained the earlier step. Return the
	 * List of products (This list will now be sorted by the category ratings.)
	 * 
	 */
	private List<Product> getTopRatedProductsFromOrderLines(List<OrderLine> orderLines) {
		// Map for Category vs Aggregate ratings. For example, Furniture->22 will be the
		// sum of ratings for all items in furniture category
		Map<String, Double> categoryRatings = new HashMap<String, Double>();
		// Map of Category vs Count ratings. For example if there are 4 items with
		// Category furniture in
		// Order lines, the value will be Furniture->4.
		Map<String, Integer> categoryCounts = new HashMap<>();
		// Populate the above maps by iterating over order lines.
		for (OrderLine orderLine : orderLines) {
			String category = orderLine.getProduct().getCategory();
			categoryRatings.put(category, categoryRatings.getOrDefault(category, 0.0) + orderLine.getRating());
			categoryCounts.put(category, categoryCounts.getOrDefault(category, 0) + 1);
		}
		// This map will contain the average ratings for each category.
		// If sum of ratings for all items in category Furniture is 20 and the number of
		// items
		// in the furniture category in order lines are 5, then map will contain
		// Furniture->4 (20/5)
		Map<String, Double> categoryAverageRatings = new HashMap<>();
		for (Map.Entry<String, Double> entry : categoryRatings.entrySet()) {
			Double averageRating = ((Double) entry.getValue() / categoryCounts.get(entry.getKey()));
			categoryAverageRatings.put(entry.getKey(), averageRating);
		}
		// Get all products/items on sale.
		List<Product> products = productService.getAllProducts();
		// Filter products as we only want to consider the ones in order lines.
		products = products.stream().filter(o -> categoryRatings.containsKey(o.getCategory()))
				.collect(Collectors.toList());
		// Sort the products based on the average rating of category.
		// If furniture's average rating-7 & electronics' average rating=6, furniture
		// appears before electronics
		// in the list.
		Collections.sort(products, (a, b) -> Double.compare(categoryAverageRatings.get(b.getCategory()),
				categoryAverageRatings.get(a.getCategory())));

		return products;
	}
}
