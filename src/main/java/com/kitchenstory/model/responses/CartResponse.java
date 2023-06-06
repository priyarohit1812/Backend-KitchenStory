package com.kitchenstory.model.responses;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kitchenstory.model.Cart;
import com.kitchenstory.model.OrderItem;

public class CartResponse {
	@JsonIgnoreProperties("user")
	private Cart cart;
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler","cart"})
	private List<OrderItem> orderItems;
	
	public CartResponse() {
		this(null,null);
	}
	
	public CartResponse(Cart cart, List<OrderItem> orderItems) {
		this.cart = cart;
		this.orderItems = orderItems;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}
}
