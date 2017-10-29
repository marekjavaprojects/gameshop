package com.gameshop.model;

import java.math.BigDecimal;

import com.gameshop.entity.Product;

public class CartItem {

	private long id;
	private String productName;
	private String category;
	private int quantity;
	private BigDecimal unitPrice;
	private BigDecimal subtotalPrice;
	private String pathToImage;

	public CartItem(Product product) {
		this.id = product.getProductId();
		this.productName = product.getProductName();
		this.category = product.getCategory();
		this.quantity = 1;
		this.unitPrice = product.getUnitPrice();
		this.subtotalPrice = calculateTotalPrice();
		this.pathToImage = product.getPathToImage();
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
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

	private BigDecimal calculateTotalPrice() {
		return unitPrice.multiply(BigDecimal.valueOf(quantity));
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

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public BigDecimal getSubtotalPrice() {
		return subtotalPrice.multiply(BigDecimal.valueOf(quantity));
	}

	public void setSubtotalPrice(BigDecimal subtotalPrice) {
		this.subtotalPrice = subtotalPrice;
	}
}