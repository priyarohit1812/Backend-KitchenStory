package com.kitchenstory.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kitchenstory.model.FoodItem;
import com.kitchenstory.model.responses.BaseResponse;
import com.kitchenstory.service.IFoodItemService;

@RestController()
@RequestMapping("/user/fooditem")
public class FoodItemController {
	
	@Autowired
	private IFoodItemService foodItemService;
	
	@GetMapping("/list")
	public ResponseEntity<BaseResponse<List<FoodItem>>> getAllFoodItems(){
		BaseResponse<List<FoodItem>> response = new BaseResponse<List<FoodItem>>();
		try {
			List<FoodItem> allFoodItems= this.foodItemService.fetchFoodItemList();
			if (allFoodItems == null || allFoodItems.isEmpty() ) {
				response.setMessage("No foodItem found");
			}
			
			response.setIsError(false);
			response.setResponse(allFoodItems);
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			response.setIsError(true);
			response.setMessage("Could not fetch FoodItems." + e.getMessage());
			response.setResponse(null);
			return ResponseEntity.internalServerError().body(response);
		}
			
	}	
		
	@GetMapping("/{foodItemId}")
	public ResponseEntity<BaseResponse<FoodItem>> getfoodItem(@PathVariable Long foodItemId){
		BaseResponse<FoodItem> response = new BaseResponse<FoodItem>();
		if (foodItemId<=0) {
			response.setIsError(true);
			response.setMessage("Please provide foodItem id to fetch foodItem");
			return ResponseEntity.badRequest().body(response);
		}
		
		FoodItem foodItem = this.foodItemService.getFoodItemById(foodItemId);
		if (foodItem==null) {
			response.setIsError(true);
			response.setMessage("Could not fetch the requested foodItem");
			return ResponseEntity.internalServerError().body(response);
		}
		
		response.setIsError(false);
		response.setMessage("FoodItem found!");
		response.setResponse(foodItem);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/list/{foodCategoryId}")
	public ResponseEntity<BaseResponse<List<FoodItem>>> getAllFoodItems(@PathVariable Long foodCategoryId) {
		BaseResponse<List<FoodItem>> response = new BaseResponse<List<FoodItem>>();
		try {
			if (foodCategoryId <= 0) {
				response.setIsError(true);
				response.setMessage("Please provide food category id to fetch results");
				return ResponseEntity.badRequest().body(response);
			}
			List<FoodItem> allFoodItems = this.foodItemService.getFoodItemByFoodCategory(foodCategoryId);
			if (allFoodItems == null || allFoodItems.isEmpty()) {
				response.setMessage("No result found");
			}
			response.setIsError(false);
			response.setResponse(allFoodItems);
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			response.setIsError(true);
			response.setMessage("Could not fetch foodItems. " + e.getMessage());
			response.setResponse(null);
			return ResponseEntity.internalServerError().body(response);
		}
	}
	
	@GetMapping("/searchByBrand/{key}")
	public ResponseEntity<BaseResponse<List<FoodItem>>>searchFoodItemByBrand(@PathVariable String key){
		BaseResponse<List<FoodItem>> response = new BaseResponse<List<FoodItem>>();
		try {
			if (key.isBlank()) {
				response.setIsError(true);
				response.setMessage("Please provide brand name to fetch results");
				return ResponseEntity.badRequest().body(response);
			}
			List<FoodItem> fooditemByBrand = this.foodItemService.fetchFoodItemListByBrand(key);
			
			if (fooditemByBrand == null || fooditemByBrand.isEmpty()) {
				response.setMessage("No result found");
			}
			response.setIsError(false);
			response.setResponse(fooditemByBrand);
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			response.setIsError(true);
			response.setMessage("Could not fetch foodItems. " + e.getMessage());
			response.setResponse(null);
			return ResponseEntity.internalServerError().body(response);
		}
		
	}
	
	@GetMapping("/search/{key}")
	public ResponseEntity<BaseResponse<List<FoodItem>>>searchFoodItem(@PathVariable String key){
		BaseResponse<List<FoodItem>> response = new BaseResponse<List<FoodItem>>();
		try {
			if (key.isBlank()) {
				response.setIsError(true);
				response.setMessage("Please provide key to fetch results");
				return ResponseEntity.badRequest().body(response);
			}
			List<FoodItem> fooditems = this.foodItemService.searchFoodItem(key);
			
			if (fooditems == null || fooditems.isEmpty()) {
				response.setMessage("No result found");
			}
			response.setIsError(false);
			response.setResponse(fooditems);
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			response.setIsError(true);
			response.setMessage("Could not fetch foodItems. " + e.getMessage());
			response.setResponse(null);
			return ResponseEntity.internalServerError().body(response);
		}
		
	}
	
}
