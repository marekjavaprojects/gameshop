package com.gameshop.service;

import java.util.Map;

import com.gameshop.entity.Product;
import com.gameshop.entity.User;
import com.gameshop.model.CartItem;
import com.gameshop.model.ShoppingCart;

public interface OrderService {
	
	public Product transformCartItemIntoProduct(CartItem cartItem);
	
	public void processOrderIntoDatabase(ShoppingCart shoppingCart, User user);

}
