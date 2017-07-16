package com.gameshop.service;

import java.util.List;
import java.util.Set;

import com.gameshop.entity.Product;

public interface ProductService {

	public List<Product> getProducts();

	public Product getProductById(int productId);

	public Set<String> fetchCategoriesFromProducts(List<Product> products);

	public List<Product> getFourLatestProducts();

	public List<Product> getProductsByCategory(String category);

	public List<Product> searchProductsByName(String productName);

}
