package com.gameshop.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.gameshop.model.CartItem;
import com.gameshop.model.ShoppingCart;

@Service
@Scope("session")
public class ShoppingCartServiceImpl implements ShoppingCartService {

	@Autowired
	private ShoppingCart shoppingCart;
	private BigDecimal totalPrice = BigDecimal.ZERO;

	@Override
	public void addToCart(CartItem cartItem) {
		totalPrice = BigDecimal.ZERO;
		if (!isAlreadyInCart(shoppingCart, cartItem)) {
			shoppingCart.addProduct(cartItem);
			for (CartItem item : shoppingCart.getCartItems()) {
				totalPrice = totalPrice.add(item.getSubtotalPrice());
			}
			shoppingCart.setTotalPrice(totalPrice);
		}
	}

	@Override
	public void updateCartItem(String[] quantity) {
		totalPrice = BigDecimal.ZERO;
		List<CartItem> cartItems = shoppingCart.getCartItems();
		for (int i = 0; i < cartItems.size(); i++) {
			cartItems.get(i).setQuantity(Integer.parseInt(quantity[i]));
		}
		for (CartItem item : cartItems) {

			totalPrice = totalPrice.add(item.getSubtotalPrice());

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
		//todo: refactor return null
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
		totalPrice = totalPrice.subtract(cartItem.getSubtotalPrice());
		shoppingCart.deleteProduct(cartItem);
		totalPrice = BigDecimal.ZERO;
	}

}
