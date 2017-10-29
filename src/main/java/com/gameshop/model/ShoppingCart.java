package com.gameshop.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import org.springframework.context.annotation.ScopedProxyMode;

@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS, value = "session")
public class ShoppingCart implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<CartItem> cartItems = new ArrayList<CartItem>();
	private BigDecimal totalPrice = new BigDecimal(0);
	private int numberOfItemsInCart;

	public ShoppingCart(BigDecimal totalPrice, int numberOfItemsInCart) {
		this.totalPrice = totalPrice;
		this.numberOfItemsInCart = numberOfItemsInCart;
	}

	public ShoppingCart() {

	}

	public List<CartItem> getCartItems() {
		return cartItems;
	}

	public void setCartItems(List<CartItem> cartItems) {
		this.cartItems = cartItems;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public int getNumberOfItemsInCart() {
		return numberOfItemsInCart;
	}

	public void setNumberOfItemsInCart(int numberOfItemsInCart) {
		this.numberOfItemsInCart = numberOfItemsInCart;
	}

	public void addProduct(CartItem cartItem) {

		this.cartItems.add(cartItem);
		this.totalPrice = totalPrice.add(cartItem.getSubtotalPrice());

	}

	public void deleteProduct(CartItem cartItem) {
		this.cartItems.remove(cartItem);
		this.totalPrice = totalPrice.subtract(cartItem.getSubtotalPrice());
	}
}