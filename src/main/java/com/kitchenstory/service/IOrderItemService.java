package com.kitchenstory.service;

import java.util.List;

import com.kitchenstory.model.OrderItem;

public interface IOrderItemService {
	public List<OrderItem> fetchOrderItemList();
	public List<OrderItem> fetchOrderItemsByCart(Long cartId);
	public OrderItem getOrderItemById(Long orderItemId);
	public OrderItem saveOrderItem(OrderItem orderItem);
	public boolean deleteOrderItem(Long orderItemId);
}
