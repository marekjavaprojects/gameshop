package com.gameshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gameshop.entity.Product;
import com.gameshop.model.ShoppingCart;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

	@Autowired
	private ShoppingCart shoppingCart;

	@Override
	public void addToCart(Product product) {
		
		if(!isAlreadyInCart(shoppingCart, product)) {
		shoppingCart.addProduct(product);
		}
		
	}

	public boolean isAlreadyInCart(ShoppingCart shoppingCart, Product product) {
		for (Product p : shoppingCart.getProducts()) {
			if (p.getId() == product.getId()) {
				return false;
			}
		}
		return true;
	}
}
