package com.kitchenstory.model;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "ks_foodcategory")
public class FoodCategory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long foodCategoryId;
	@NotBlank(message = "Category name is mandatory")
	private String categoryName;
	
	
	@OneToMany(mappedBy = "foodCategory", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JsonIgnoreProperties("foodCategory")
	private Set<FoodItem> foodItems = new HashSet<>();
	
	public Long getFoodCategoryId() {
		return foodCategoryId;
	}


	public void setFoodCategoryId(Long foodCategoryId) {
		this.foodCategoryId = foodCategoryId;
	}


	public String getCategoryName() {
		return categoryName;
	}


	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	
	public FoodCategory(Long foodCategoryId, @NotBlank(message = "Category name is mandatory") String categoryName
			) {
		this.foodCategoryId = foodCategoryId;
		this.categoryName = categoryName;
		
	}


	public FoodCategory() {
		this(0L,"");
	}


	@Override
	public String toString() {
		return "FoodCategory [foodCategoryId=" + foodCategoryId + ", categoryName=" + categoryName + "]";
	}

	
}
