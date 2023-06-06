package com.kitchenstory.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "ks_fooditem")
public class FoodItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long foodItemId;
	@NotBlank(message = "FoodItem code is mandatory")
	private String foodItemCode;
	@NotBlank(message = "foodItem name is mandatory")
	private String foodItemName;
	@NotBlank(message = "Brand is mandatory")
	private String brand;
	@NotNull(message = "Weight is mandatory")
	private String weight;
	
	private String image_url;
	@NotNull(message = "Price is mandatory")
	private double price;
	
	@ManyToOne
	@JoinColumn(name = "foodCategory_id", nullable = true)
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler","foodItems"})
	private FoodCategory foodCategory;
	
	
	public Long getFoodItemId() {
		return foodItemId;
	}


	public void setFoodItemId(Long foodItemId) {
		this.foodItemId = foodItemId;
	}


	public String getFoodItemCode() {
		return foodItemCode;
	}


	public void setFoodItemCode(String foodItemCode) {
		this.foodItemCode = foodItemCode;
	}


	public String getFoodItemName() {
		return foodItemName;
	}


	public void setFoodItemName(String foodItemName) {
		this.foodItemName = foodItemName;
	}


	public String getBrand() {
		return brand;
	}


	public void setBrand(String brand) {
		this.brand = brand;
	}


	public String getWeight() {
		return weight;
	}


	public void setWeight(String weight) {
		this.weight = weight;
	}


	public String getImage_url() {
		return image_url;
	}


	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	public FoodCategory getFoodCategory() {
		return foodCategory;
	}


	public void setFoodCategory(FoodCategory foodCategory) {
		this.foodCategory = foodCategory;
	}

	
	public FoodItem(Long foodItemId,String foodItemCode, String foodItemName, String brand, String weight,String image_url,
			double price) {
		this.foodItemId = foodItemId;
		this.foodItemCode = foodItemCode;
		this.foodItemName = foodItemName;
		this.brand = brand;
		this.weight = weight;
		this.image_url = image_url;
		this.price = price;
	}


	public FoodItem() {
		this(0L, "", "", "", "", "", 0.0);
	}


	@Override
	public String toString() {
		return "FoodItem [foodItemId=" + foodItemId + ", foodItemCode=" + foodItemCode + ", foodItemName="
				+ foodItemName + ", brand=" + brand + ", weight=" + weight + ", image_url=" + image_url + ", price="
				+ price + ", foodCategory=" + foodCategory + "]";
	}

}
