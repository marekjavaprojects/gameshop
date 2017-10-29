package com.gameshop.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Digits;

@Entity
@Table(name = "orders")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_id", nullable = false)
	private Long orderId;

	@Column(name = "total_price", nullable = false)
	@Digits(integer = 10, fraction = 2)
	private BigDecimal totalPrice;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@OneToMany(mappedBy = "order", cascade=CascadeType.ALL)
	private List<OrderDetails> orderDetails;

	public List<OrderDetails> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderDetails> orderDetails) {
		this.orderDetails = orderDetails;
	}

	public Long getId() {
		return orderId;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotal(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Order(BigDecimal totalPrice, User user) {
		this.orderDetails = new ArrayList<OrderDetails>();
		this.totalPrice = totalPrice;
		this.user = user;
	}

	public Order() {
		this.orderDetails = new ArrayList<OrderDetails>();
	}

	@Override
	public String toString() {
		return "Order [id=" + orderId + ", total price=" + totalPrice + ", user=" + user;
	}
}
