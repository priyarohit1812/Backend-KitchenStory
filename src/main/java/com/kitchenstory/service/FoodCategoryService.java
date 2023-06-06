package com.kitchenstory.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kitchenstory.model.FoodCategory;
import com.kitchenstory.repository.IFoodCategoryRepository;

@Service(value = "foodCategoryService")
public class FoodCategoryService implements IFoodCategoryService {
	@Autowired
	private IFoodCategoryRepository foodCategoryRepository;
	
	@Override
	public List<FoodCategory> fetchFoodCategoryList() {
		return this.foodCategoryRepository.findAll();
	}

	@Override
	public FoodCategory saveFoodCategory(FoodCategory foodCategory) {
		return this.foodCategoryRepository.save(foodCategory);
	}

	@Override
	public boolean deleteFoodCategory(Long foodCategoryId) {
		this.foodCategoryRepository.deleteById(foodCategoryId);
		return !this.foodCategoryRepository.existsById(foodCategoryId) ;
	}

	@Override
	public FoodCategory getFoodCategoryById(Long foodCategoryId) {
		Optional<FoodCategory> optFoodCategory = this.foodCategoryRepository.findById(foodCategoryId);
		if (optFoodCategory !=null && optFoodCategory.isPresent()) {
			return optFoodCategory.get();
		}else {
			return null;
		}
	}

	@Override
	public List<FoodCategory> searchFoodCategory(String key) {
		return this.foodCategoryRepository.findByCategoryNameContains(key);
	}

	@Override
	public FoodCategory searchFoodCategoryByName(String key) {
		Optional<FoodCategory> optFC = this.foodCategoryRepository.findByCategoryName(key);
		if (optFC!=null && optFC.isPresent()) {
			return optFC.get();
		}
		return null;
	}

	
	@Override
	public List<String> getCategoryNames() {
		return this.foodCategoryRepository.getDistictCategoryName();
	}
}
