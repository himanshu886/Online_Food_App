package com.himanshu.request;

import com.himanshu.model.Address;

import lombok.Data;

@Data
public class OrderRequest {
	
		private Long restaurantId;
		
		private Address deliveryAddress;
		
		
}
