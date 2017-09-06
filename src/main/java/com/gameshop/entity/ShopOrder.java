package com.gameshop.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "shop_order")
public class ShopOrder {

	public ShopOrder() {

	}

	public ShopOrder(int orderId, double amount, String orderDate, User user) {
		super();
		this.orderId = orderId;
		this.amount = amount;
		this.orderDate = orderDate;
		this.user = user;
	}

	@Id
	@Column(name = "order_id")
	private int orderId;

	@Column(name = "amount")
	private double amount;

	@Column(name = "order_date")
	private String orderDate;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "username")
	private User user;

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
