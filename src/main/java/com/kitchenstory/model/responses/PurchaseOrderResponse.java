package com.kitchenstory.model.responses;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kitchenstory.model.OrderItem;
import com.kitchenstory.model.PurchaseOrder;

public class PurchaseOrderResponse {
	private PurchaseOrder order;
	@JsonIgnoreProperties("cart")
	private List<OrderItem> orderItems;

	public PurchaseOrderResponse() {
		this(null,null);
	}

	public PurchaseOrderResponse(PurchaseOrder order, List<OrderItem> orderItems) {
		this.order = order;
		this.orderItems = orderItems;
	}

	public PurchaseOrder getOrder() {
		return order;
	}

	public void setOrder(PurchaseOrder order) {
		this.order = order;
	}

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}
}
