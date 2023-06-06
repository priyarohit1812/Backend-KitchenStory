package com.kitchenstory.model.requests;

public class AddCartRequest {
	
	private Long userId;
	private Long foodItemId;
	
	public AddCartRequest(Long userId, Long foodItemId) {
		this.userId = userId;
		this.foodItemId = foodItemId;
	}
	public AddCartRequest() {
		this(0L,0L);
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getFoodItemId() {
		return foodItemId;
	}
	public void setFoodItemId(Long foodItemId) {
		this.foodItemId = foodItemId;
	}
	
	
}
