package com.kitchenstory.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kitchenstory.model.FoodItem;
import com.kitchenstory.model.responses.BaseResponse;
import com.kitchenstory.service.IFoodItemService;

@RestController()
@RequestMapping("/admin/fooditem")
public class AdminFoodItemController {
	
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
	
	@PostMapping("/save")
	public ResponseEntity<BaseResponse<FoodItem>> saveFoodItem(@RequestBody FoodItem foodItem){
		BaseResponse<FoodItem> response = new BaseResponse<FoodItem>();
		if (foodItem==null) {
			response.setIsError(true);
			response.setMessage("Request object is null");
			return ResponseEntity.badRequest().body(response);
		}
		
		FoodItem savedFoodItem = this.foodItemService.saveFoodItem(foodItem);
		if (savedFoodItem == null) {
			response.setIsError(true);
			response.setMessage("Could not save the requested foodItem");
			return ResponseEntity.internalServerError().body(response);
			
		}
		
		response.setIsError(false);
		response.setMessage("FoodItem saved successfully!");
		response.setResponse(savedFoodItem);
		return ResponseEntity.ok(response);		
	}
	
	@PostMapping("/upload/image")
	public ResponseEntity<BaseResponse<?>> uploadImage(@RequestParam("file") MultipartFile file, @RequestParam("foodItemId")Long foodItemId){
		BaseResponse<?> response = new BaseResponse<>();
		String basePath = "F:\\JavaWorkspace\\KitchenStrory\\src\\main\\resources\\static\\images\\fooditems\\";
		String baseURL = "http://localhost:8091/images/fooditems/";
		try {
			// Get Food Item
			FoodItem foodItem = this.foodItemService.getFoodItemById(foodItemId);
			String fileName = foodItem.getFoodItemCode() + "_"+ foodItem.getFoodItemId() + "_"+ file.getOriginalFilename();
			//Save file to path
			file.transferTo(new File(basePath + fileName));
			//Update URL in DB
			foodItem.setImage_url(baseURL + fileName);
			this.foodItemService.saveFoodItem(foodItem);
			response.setIsError(false);
			response.setMessage("Image uploaded succesfully.");
			return ResponseEntity.ok(response);
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
			response.setIsError(true);
			response.setMessage(e.getMessage());
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
	
	@DeleteMapping("/{foodItemId}")
	public ResponseEntity<BaseResponse<?>> deleteFoodItem(@PathVariable Long foodItemId ){
		BaseResponse<FoodItem> response = new BaseResponse<FoodItem>();
		if (foodItemId<=0) {
			response.setIsError(true);
			response.setMessage("Please provide foodItem id to fetch foodItem");
			return ResponseEntity.badRequest().body(response);
		}
		
		boolean isDeleted = this.foodItemService.deleteFoodItem(foodItemId);
		if (isDeleted) {
			response.setIsError(true);
			response.setMessage("FoodItem deleted successfully!");
			return ResponseEntity.ok(response);
		}else {
			response.setIsError(true);
			response.setMessage("Could not delete the requested foodItem");
			return ResponseEntity.internalServerError().body(response);
		}
		
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
