package com.gameshop.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import org.springframework.context.annotation.ScopedProxyMode;

@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS, value = "session")
public class ShoppingCart implements Serializable{


	private static final long serialVersionUID = 1L;
	
	private List<CartItem> cartItems = new ArrayList<CartItem>();
	private double totalPrice;
	private int numberOfItemsInCart;

	public ShoppingCart() {

	}

	public ShoppingCart( double totalPrice, int numberOfItemsInCart) {
		this.totalPrice = totalPrice;
		this.numberOfItemsInCart = numberOfItemsInCart;
	}

	public List<CartItem> getCartItems() {
		return cartItems;
	}

	public void setCartItems(List<CartItem> cartItems) {
		this.cartItems = cartItems;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {		
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
		this.totalPrice += cartItem.getSubtotalPrice();

	}
	
	public void deleteProduct(CartItem cartItem) {
		this.cartItems.remove(cartItem);
		this.totalPrice -= cartItem.getSubtotalPrice();
	}
}