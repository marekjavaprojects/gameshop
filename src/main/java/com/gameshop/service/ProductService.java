package com.gameshop.service;

import java.util.List;
import java.util.Set;

import com.gameshop.entity.Product;

public interface ProductService {

	public List<Product> getProducts();

	public List<Product> getLatestAvailableProducts();

	public Product getProductById(Long productId);

	public Set<String> fetchCategoriesFromProducts(List<Product> products);

	public List<Product> getProductsByCategory(String category);

	public List<Product> searchProductsByName(String productName);

	public Product findProductByName(String productName);

}
