package com.himanshu.request;

import java.util.List;

import com.himanshu.model.Address;
import com.himanshu.model.ContactInformation;

import lombok.Data;

@Data
public class CreateRestaurantRequest {
	
		private Long id;
		private String name;
		private String description;
		private String cuisineType;
		private Address address;
		private ContactInformation contactInformation;
		private String openingHours;
		private List<String> images;
		
}
