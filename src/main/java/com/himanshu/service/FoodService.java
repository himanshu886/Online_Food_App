package com.himanshu.service;

import java.util.List;

import com.himanshu.model.Category;
import com.himanshu.model.Food;
import com.himanshu.model.Restaurant;
import com.himanshu.request.CreateFoodRequest;

public interface FoodService {
	
		public Food createFood(CreateFoodRequest req, Category category, Restaurant restaurant);
		
		void deleteFood(Long foodId) throws Exception;
		
		public List<Food> getRestaurantsFood(Long restaurantId, boolean isVegetarian, boolean isNonveg, boolean isSeasonal, String foodCategory);
		
		public List<Food> searchFood(String keyword);
		
		public Food findFoodById(Long foodId) throws Exception;
		
		public Food updateAvailibilityStatus(Long foodId) throws Exception;
		
}
