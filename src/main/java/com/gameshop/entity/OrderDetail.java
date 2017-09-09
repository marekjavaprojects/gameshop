package com.gameshop.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "order_detail")
public class OrderDetail {

	public OrderDetail() {

	}

	public OrderDetail(int orderDetailId, ShopOrder order, Product product) {
		super();
		this.orderDetailId = orderDetailId;
		this.order = order;
		this.product = product;
	}

	@Id
	@Column(name = "order_detail_id")
	private int orderDetailId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_id", nullable = false, //
			foreignKey = @ForeignKey(name = "order_id"))
	private ShopOrder order;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id", nullable = false, //
			foreignKey = @ForeignKey(name = "product_id"))
	private Product product;

	public int getOrderDetailId() {
		return orderDetailId;
	}

	public void setOrderDetailId(int orderDetailId) {
		this.orderDetailId = orderDetailId;
	}

	public ShopOrder getOrder() {
		return order;
	}

	public void setOrder(ShopOrder order) {
		this.order = order;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}
