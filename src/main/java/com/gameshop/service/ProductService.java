package com.gameshop.service;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import com.gameshop.entity.Product;

public interface ProductService {
		
	public Page<Product> getProducts(Pageable pageable);
	
	public Page<Product> getLatestAddedProducts(Pageable pageable);

	public Page<Product> getLatestAvailableProducts(Pageable pageable);

	public Product getProductById(Long productId);

	public Set<String> getProductCategories();

	public Page<Product> getProductsByCategory(String category, Pageable pageable);

	public Page<Product> getProductsByName(String productName, Pageable pageable);

	public Product getProductByName(String productName);

}
