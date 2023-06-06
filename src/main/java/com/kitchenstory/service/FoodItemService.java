package com.kitchenstory.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kitchenstory.model.FoodItem;
import com.kitchenstory.repository.IFoodItemRepository;

@Service(value ="fooditemService")
public class FoodItemService implements IFoodItemService {
	@Autowired
	private IFoodItemRepository foodItemRepository;
	
	
	@Override
	public List<FoodItem> fetchFoodItemList() {
		return this.foodItemRepository.findAll();
	}

	@Override
	public FoodItem getFoodItemById(Long productId) {
		Optional<FoodItem> optFoodItem = this.foodItemRepository.findById(productId);
		if (optFoodItem !=null && optFoodItem.isPresent()) {
			return optFoodItem.get();
		}else {
			return null;
		}
	}

	@Override
	public FoodItem saveFoodItem(FoodItem product) {
		return this.foodItemRepository.save(product);
	}

	@Override
	public boolean deleteFoodItem(Long productId) {
		this.foodItemRepository.deleteById(productId);
		return !this.foodItemRepository.existsById(productId);
	}

	@Override
	public List<FoodItem> searchFoodItem(String key) {
		return this.foodItemRepository.findByFoodItemNameContainsOrBrandContains(key, key);
	}

	@Override
	public List<String> getBrands() {
		return this.foodItemRepository.getDistictBrand();
	}

	@Override
	public List<FoodItem> fetchFoodItemListByBrand(String brand) {
		return this.foodItemRepository.findByBrand(brand);
	}

	@Override
	public List<FoodItem> getFoodItemByFoodCategory(Long foodCategoryId) {
		return this.foodItemRepository.getFoodItemByFoodCategory(foodCategoryId);
	}
	
	
	
	

	/*@Override
	public List<FoodItem> getAllFoodItemByCartId(Long cartId) {
		return this.foodItemRepository.findAllFoodItemBycartId(cartId);
	}

	@Override
	public List<FoodItem> getAllFoodItemByFoodItemCategory(FoodItemCategory productCategoryId) {
		return this.foodItemRepository.findAllFoodItemByFoodItemCategory(productCategoryId);
	}*/

}
