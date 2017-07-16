package com.gameshop.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gameshop.dao.ProductDAO;
import com.gameshop.entity.Product;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductDAO productDAO;

	@Override
	@Transactional
	public List<Product> getProducts() {
		return productDAO.getProducts();
	}

	@Override
	@Transactional
	public Product getProductById(int productId) {
		return productDAO.getProductById(productId);
	}

	@Override
	@Transactional
	public List<Product> getFourLatestProducts() {
		return productDAO.getFourLatestProducts();
	}
	
	@Override
	@Transactional
	public List<Product> getProductsByCategory(String category) {
		return productDAO.getProductsByCategory(category);
	}
	
	public Set<String> fetchCategoriesFromProducts(List<Product> products) {

		Set<String> categories = new HashSet<>();
		for (Product product : products) {
			categories.add(product.getCategory());
		}
		return categories;
	}
	
	@Override
	@Transactional
	public List<Product> searchProductsByName(String productName) {
		
		return productDAO.searchProductsByName(productName);

	}
}
