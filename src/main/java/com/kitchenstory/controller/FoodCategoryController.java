package com.kitchenstory.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kitchenstory.model.FoodCategory;
import com.kitchenstory.model.responses.BaseResponse;
import com.kitchenstory.service.IFoodCategoryService;

@RestController()
@RequestMapping("/user/foodcategory")
public class FoodCategoryController {
	
	@Autowired
	private IFoodCategoryService foodCategoryService;
	
	@GetMapping("/list")
	public ResponseEntity<BaseResponse<List<FoodCategory>>> getAllFoodCategories(){
		BaseResponse<List<FoodCategory>> response = new BaseResponse<List<FoodCategory>>();
		try {
			List<FoodCategory> allFoodCategories= this.foodCategoryService.fetchFoodCategoryList();
			if (allFoodCategories == null || allFoodCategories.isEmpty() ) {
				response.setMessage("No foodCategory found");
			}
			
			response.setIsError(false);
			response.setResponse(allFoodCategories);
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			response.setIsError(true);
			response.setMessage("Could not fetch FoodCategories." + e.getMessage());
			response.setResponse(null);
			return ResponseEntity.internalServerError().body(response);
		}
			
	}
	
	@GetMapping("/{foodCategoryId}")
	public ResponseEntity<BaseResponse<FoodCategory>> getfoodItem(@PathVariable Long foodCategoryId){
		BaseResponse<FoodCategory> response = new BaseResponse<FoodCategory>();
		if (foodCategoryId<=0) {
			response.setIsError(true);
			response.setMessage("Please provide foodCategory id to fetch foodCategory");
			return ResponseEntity.badRequest().body(response);
		}
		
		FoodCategory foodCategory = this.foodCategoryService.getFoodCategoryById(foodCategoryId);
		if (foodCategory==null) {
			response.setIsError(true);
			response.setMessage("Could not fetch the requested foodCategory");
			return ResponseEntity.internalServerError().body(response);
		}
		
		response.setIsError(false);
		response.setMessage("FoodCategory found!");
		response.setResponse(foodCategory);
		return ResponseEntity.ok(response);
	}
}
