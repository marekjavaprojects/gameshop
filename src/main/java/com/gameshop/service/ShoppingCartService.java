package com.gameshop.service;

import org.springframework.stereotype.Component;


import com.gameshop.entity.Product;

public interface ShoppingCartService {
	
	public void addToCart(Product product);
	
}
