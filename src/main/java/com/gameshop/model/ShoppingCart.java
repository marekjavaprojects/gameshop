package com.gameshop.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gameshop.entity.Product;

import org.springframework.context.annotation.ScopedProxyMode;

@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS, value = "session")
public class ShoppingCart implements Serializable{


	private static final long serialVersionUID = 1L;
	
	private List<Product> products = new ArrayList<Product>();
	private double totalPrice;
	private int numberOfProducts;

	public ShoppingCart() {

	}

	public ShoppingCart( double totalPrice, int numberOfProducts) {
		this.totalPrice = totalPrice;
		this.numberOfProducts = numberOfProducts;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
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
	
	public void addProduct(Product product) {
		this.products.add(product);
	}
}

