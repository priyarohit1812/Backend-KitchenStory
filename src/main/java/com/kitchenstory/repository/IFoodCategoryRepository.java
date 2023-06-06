package com.kitchenstory.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kitchenstory.model.FoodCategory;


public interface IFoodCategoryRepository extends JpaRepository<FoodCategory, Long> {
	List<FoodCategory> findByCategoryNameContains(String categoryName);
	Optional<FoodCategory> findByCategoryName(String categoryName);
	
	
	
	@Query("SELECT DISTINCT(fc.categoryName) FROM FoodCategory fc")
	List<String> getDistictCategoryName();
}
