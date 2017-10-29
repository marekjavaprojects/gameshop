package com.gameshop.service;

import com.gameshop.model.CartItem;

public interface ShoppingCartService {

	public void addToCart(CartItem cartItem);

	public CartItem findCartItemByName(String productName);

	public CartItem findCartItemById(Long productId);

	public void updateCartItem(String[] quantity);

	public void deleteItemFromCart(CartItem cartItem);

}
