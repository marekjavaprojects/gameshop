package com.gameshop.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Digits;

@Entity
@Table(name = "product")
public class Product {

	public Product() {
	}

	public Product(Long id, String productName, String category, BigDecimal  unitPrice, int quantity,
			String pathToImage, String dateAdded) {
		this.id = id;
		this.productName = productName;
		this.category = category;
		this.unitPrice = unitPrice;
		this.quantity = quantity;
		this.pathToImage = pathToImage;
		this.dateAdded = dateAdded;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "product_name")
	private String productName;

	@Column(name = "category")
	private String category;

	@Column(name = "unit_price")
	@Digits(integer = 10, fraction = 2)
	private BigDecimal unitPrice;

	@Column(name = "quantity")
	private int quantity;

	@Column(name = "path_to_image")
	private String pathToImage;

	@Column(name = "date_added")
	private String dateAdded;

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public BigDecimal  getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal  unitPrice) {
		this.unitPrice = unitPrice;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getPathToImage() {
		return pathToImage;
	}

	public void setPathToImage(String pathToImage) {
		this.pathToImage = pathToImage;
	}

	public Long getProductId() {
		return id;
	}

	public String getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(String dateAdded) {
		this.dateAdded = dateAdded;
	}

	@Override
	public String toString() {
		return "Product [productId=" + id + ", productName=" + productName + ", category=" + category
				+ ", unitPrice=" + unitPrice + ", quantity=" + quantity + ", pathToImage=" + pathToImage + "]";
	}

}
