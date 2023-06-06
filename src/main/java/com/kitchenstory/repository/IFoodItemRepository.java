package com.kitchenstory.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kitchenstory.model.FoodItem;

public interface IFoodItemRepository extends JpaRepository<FoodItem, Long> {
	
	List<FoodItem> findByFoodItemNameContainsOrBrandContains(String foodItemName, String brand);
	List<FoodItem> findByBrand(String brand);
	
	@Query("SELECT DISTINCT(fi.brand) FROM FoodItem fi")
	List<String> getDistictBrand();
	
	@Query(nativeQuery = true, value = "SELECT foo.* FROM ks_fooditem foo WHERE foo.food_category_id = :foodCategoryId")
	List<FoodItem> getFoodItemByFoodCategory(@Param("foodCategoryId") Long foodCategoryId);
}
