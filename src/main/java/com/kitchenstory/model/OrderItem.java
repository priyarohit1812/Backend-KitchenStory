package com.kitchenstory.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "ks_orderitem")
public class OrderItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long orderItemId;
	@NotNull(message = "Quantity is mandatory")
	private int quantity;
	@NotNull(message = "Total Price is mandatory")
	private double totalPrice;

	@ManyToOne
	@JoinColumn(name = "foodItem_id_fk")
	@NotNull(message = "foodItem Reference is mandatory")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private FoodItem foodItem;
	
	@ManyToOne
	@JoinColumn(name = "cart_id_fk")
	@NotNull(message = "Cart Reference is mandatory")
	private Cart cart;

	public Long getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(Long orderItemId) {
		this.orderItemId = orderItemId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public FoodItem getFoodItem() {
		return foodItem;
	}

	public void setFoodItem(FoodItem foodItem) {
		this.foodItem = foodItem;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public OrderItem(Long orderItemId, int quantity, double totalPrice, FoodItem foodItem ) {
		this.orderItemId = orderItemId;
		this.quantity = quantity;
		this.totalPrice = totalPrice;
		this.foodItem = foodItem;
	}

	public OrderItem() {
		this(0L, 0, 0.0, null);
	}

	@Override
	public String toString() {
		return "OrderItem [orderItemId=" + orderItemId + ", quantity=" + quantity + ", totalPrice=" + totalPrice
				+ ", foodItem=" + foodItem + "]";
	}
	
	

}
