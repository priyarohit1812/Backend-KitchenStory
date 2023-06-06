package com.kitchenstory.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "ks_cart")
public class Cart {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cartId;
	@NotNull(message = "noOfItems are mandatory")
	private int noOfItems;
	@NotNull(message = "Total are mandatory")
	private double totalCartPrice;

	@NotNull(message = "User reference is mandatory")
	
	@OneToOne(fetch = FetchType.EAGER)
	@JsonIgnoreProperties("cart")
	@JoinColumn(name = "user_id_fk")
	private User user;

	public Long getCartId() {
		return cartId;
	}

	public void setCartId(Long cartId) {
		this.cartId = cartId;
	}

	public int getNoOfItems() {
		return noOfItems;
	}

	public void setNoOfItems(int noOfItems) {
		this.noOfItems = noOfItems;
	}

	public double getTotalCartPrice() {
		return totalCartPrice;
	}

	public void setTotalCartPrice(double totalCartPrice) {
		this.totalCartPrice = totalCartPrice;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Cart(Long cartId, @NotNull(message = "noOfItems are mandatory") int noOfItems,
			@NotNull(message = "Total are mandatory") double totalCartPrice) {
		this.cartId = cartId;
		this.noOfItems = noOfItems;
		this.totalCartPrice = totalCartPrice;
	}

	public Cart() {
		this(0L, 0, 0.0);
	}

	@Override
	public String toString() {
		return "Cart [cartId=" + cartId + ", noOfItems=" + noOfItems + ", totalCartPrice=" + totalCartPrice + ", user="
				+ user + "]";
	}

}
