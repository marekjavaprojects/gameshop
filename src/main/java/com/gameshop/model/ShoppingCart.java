package com.gameshop.model;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.context.annotation.ScopedProxyMode;
import com.gameshop.entity.Product;

@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS, value = "session")
public class ShoppingCart {

	private List<String> products;
	private double totalPrice;
	private int numberOfProducts;

	public ShoppingCart() {

	}

	public ShoppingCart(List<String> products, double totalPrice, int numberOfProducts) {
		this.products = products;
		this.totalPrice = totalPrice;
		this.numberOfProducts = numberOfProducts;
	}

	public List<String> getProducts() {
		return products;
	}

	public void setProducts(List<String> products) {
		this.products = products;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public int getNumberOfProducts() {
		return numberOfProducts;
	}

	public void setNumberOfProducts(int numberOfProducts) {
		this.numberOfProducts = numberOfProducts;
	}
}
