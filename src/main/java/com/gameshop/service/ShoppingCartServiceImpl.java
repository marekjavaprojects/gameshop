package com.gameshop.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.gameshop.entity.Product;
import com.gameshop.model.CartItem;
import com.gameshop.model.ShoppingCart;

@Service
@Scope("session")
public class ShoppingCartServiceImpl implements ShoppingCartService {

	@Autowired
	private ShoppingCart shoppingCart;

	private double totalPrice;

	@Override
	public void addToCart(CartItem cartItem) {
		totalPrice = 0;
		if (!isAlreadyInCart(shoppingCart, cartItem)) {
			shoppingCart.addProduct(cartItem);
			for(CartItem item : shoppingCart.getCartItems()) {
			totalPrice += item.getSubtotalPrice();
			}
			shoppingCart.setTotalPrice(totalPrice);
		}
	}
	
	@Override
	public void updateCartItem(String[] quantity) {
		totalPrice = 0;
		List<CartItem> cartItems = shoppingCart.getCartItems();
		for(int i = 0 ; i < cartItems.size() ; i ++) { 
			cartItems.get(i).setQuantity(Integer.parseInt(quantity[i]));
		}
		for(CartItem item : cartItems) {
			
			totalPrice += item.getSubtotalPrice();
			
		}
		shoppingCart.setTotalPrice(totalPrice);
		shoppingCart.setCartItems(cartItems);	
	}

	@Override
	public CartItem findCartItemByName(String productName) {

		for (CartItem item : shoppingCart.getCartItems()) {
			if (item.getProductName().equals(productName)) {
				return item;
			}
		}
		return null;
	}
	
	@Override
	public CartItem findCartItemById(Long productId) {

		for (CartItem item : shoppingCart.getCartItems()) {
			if (item.getId() == productId) {
				return item;
			}
		}
		return null;
	}

	public boolean isAlreadyInCart(ShoppingCart shoppingCart, CartItem cartItem) {
		for (CartItem item : shoppingCart.getCartItems()) {
			if (item.getProductName().equals(cartItem.getProductName())) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void deleteItemFromCart(CartItem cartItem) {
		totalPrice -= cartItem.getSubtotalPrice();
		shoppingCart.deleteProduct(cartItem);
		totalPrice = 0;
	}

}
