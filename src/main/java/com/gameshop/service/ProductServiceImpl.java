package com.gameshop.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gameshop.entity.Product;
import com.gameshop.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	@Override
	@Transactional
	public List<Product> getLatestAvailableProducts() {
		
		return productRepository.findLatestAvailableProducts();				
	}

	@Override
	@Transactional
	public List<Product> showHomePageProducts(Pageable pageable) {

		return productRepository.showHomePageProducts(pageable);
	}

	@Override
	@Transactional
	public Page<Product> getProducts(Pageable pageable) {

		return productRepository.findAllProducts(pageable);
	}

	@Override
	@Transactional
	public Product getProductById(Long id) {

		return productRepository.getOne(id);
	}

	@Override
	@Transactional
	public Page<Product> getLatestAvailableProducts(Pageable pageable) {

		return productRepository.findLatestAvailableProducts(pageable);
	}

	@Override
	@Transactional
	public Page<Product> getProductsByCategory(String category, Pageable pageable) {
		return productRepository.findProductsByCategory(category, pageable);
	}

	@Override
	@Transactional
	public Product findProductByName(String productName) {

		return productRepository.findProductByName(productName);
	}

	@Override
	@Transactional
	public Page<Product> searchProductsByName(String productName, Pageable pageable) {

		return productRepository.searchProductsByName(productName, pageable);
	}

	public boolean isProductAvailable(Long id) {

		return productRepository.findOne(id).getQuantity() > 0;
	}

	@Override
	@Transactional
	public Set<String> getProductCategories() {
		Set<String> categories = new HashSet<>();
		List<Product> products = productRepository.findLatestAvailableProducts();
		for(Product p : products) {
			categories.add(p.getCategory());
		}

		return categories;
	}
}