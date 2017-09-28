package com.gameshop.model;

import java.math.BigDecimal;
import java.math.MathContext;

import com.gameshop.entity.Product;

public class CartItem {

	private long id;
	private String productName;
	private int quantity;
	private double unitPrice;
	private double subtotalPrice;
	private String pathToImage;

	public CartItem(Product product) {
		this.productName = product.getProductName();
		this.id = product.getId();
		this.quantity = 1;
		this.unitPrice = product.getUnitPrice().doubleValue();
		this.subtotalPrice = calculateTotalPrice();
		this.pathToImage = product.getPathToImage();
	}

	public long getId() {
		return id;
	}

	public String getPathToImage() {
		return pathToImage;
	}

	public void setPathToImage(String pathToImage) {
		this.pathToImage = pathToImage;
	}

	private double calculateTotalPrice() {
		return unitPrice * quantity;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public double getSubtotalPrice() {
		return subtotalPrice * quantity;
	}

	public void setSubtotalPrice(double subtotalPrice) {
		this.subtotalPrice = subtotalPrice;
	}

}
