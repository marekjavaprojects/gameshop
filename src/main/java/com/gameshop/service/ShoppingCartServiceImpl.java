package com.gameshop.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gameshop.entity.Product;
import com.gameshop.model.CartItem;
import com.gameshop.model.ShoppingCart;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

	@Autowired
	private ShoppingCart shoppingCart;

	private double totalPrice;

	@Override
	public void addToCart(CartItem cartItem) {

		if (!isAlreadyInCart(shoppingCart, cartItem)) {
			shoppingCart.addProduct(cartItem);
			totalPrice += cartItem.getTotalPrice();

			shoppingCart.setTotalPrice(totalPrice);
		}

		System.out.println(shoppingCart.getTotalPrice());

	}
	
	@Override
	public void updateCartItem(String[] quantity) {
		List<CartItem> cartItems = shoppingCart.getCartItems();
		for(int i = 0 ; i < cartItems.size() ; i ++) { 
			cartItems.get(i).setQuantity(Integer.parseInt(quantity[i]));
		}
		double totalPrice = 0;
		for(CartItem item : cartItems) {
			
			totalPrice += item.getTotalPrice();
			
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
		int index = 0;
		for(int i = 0 ; i < shoppingCart.getCartItems().size() ; i++) {
			if(cartItem.getProductName().equals(shoppingCart.getCartItems().get(i).getProductName())) {
				index = i;
			}
		}
		shoppingCart.getCartItems().remove(index);
		
	}

}
