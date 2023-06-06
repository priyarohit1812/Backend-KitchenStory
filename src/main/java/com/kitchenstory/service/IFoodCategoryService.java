package com.kitchenstory.service;

import java.util.List;

import com.kitchenstory.model.FoodCategory;

public interface IFoodCategoryService {
	public List<FoodCategory> fetchFoodCategoryList();
	public List<FoodCategory> searchFoodCategory(String key);
	public FoodCategory searchFoodCategoryByName(String key);
	public FoodCategory saveFoodCategory(FoodCategory productCategory);
	boolean deleteFoodCategory(Long productCategoryId);
	public FoodCategory getFoodCategoryById(Long productCategoryId);
	public List<String> getCategoryNames();
	
}
