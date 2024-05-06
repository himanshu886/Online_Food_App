package com.himanshu.request;

import java.util.List;

import com.himanshu.model.Category;
import com.himanshu.model.IngredientsItem;

import lombok.Data;

@Data
public class CreateFoodRequest {
	
	private String name;
	private String description;
	private Long price;
	
	private Category category;
	private List<String> images;
	
	private Long restaurantId;
	private boolean vegeterian;
	private boolean seasonal;
	private List<IngredientsItem> ingredients;
	
}
