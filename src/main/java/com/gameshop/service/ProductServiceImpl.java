package com.gameshop.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gameshop.entity.Product;
import com.gameshop.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepository productRepository;

	@Override
	@Transactional
	public List<Product> getProducts() {

		return productRepository.findAll();
	}

	@Override
	@Transactional
	public Product getProductById(Long id) {

		return productRepository.getOne(id);
	}

	@Override
	@Transactional
	public List<Product> getLatestAvailableProducts() {

		return productRepository.findLatestAvailableProducts();
	}

	@Override
	@Transactional
	public List<Product> getProductsByCategory(String category) {
		return productRepository.findProductsByCategory(category);
	}

	@Override
	@Transactional
	public Product findProductByName(String productName) {

		return productRepository.findProductByName(productName);
	}
	@Override
	@Transactional
	public List<Product> searchProductsByName(String productName) {

		return productRepository.searchProductsByName(productName);
	}

	public boolean isProductAvailable(Long id) {

		return productRepository.findOne(id).getQuantity() > 0;
	}

	public Set<String> fetchCategoriesFromProducts(List<Product> products) {

		Set<String> categories = new HashSet<>();
		for (Product product : products) {
			categories.add(product.getCategory());
		}

		return categories;
	}	
}