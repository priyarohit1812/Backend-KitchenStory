package com.kitchenstory.service;

import java.util.List;

import com.kitchenstory.model.FoodItem;


public interface IFoodItemService {
	public List<FoodItem> fetchFoodItemList();
	public List<FoodItem> fetchFoodItemListByBrand(String brand);
	public List<FoodItem> searchFoodItem(String key);
	public FoodItem getFoodItemById(Long productId);
	public FoodItem saveFoodItem(FoodItem product);
	public boolean deleteFoodItem(Long productId);
	public List<String> getBrands();
	public List<FoodItem> getFoodItemByFoodCategory(Long foodCategoryId);
}
