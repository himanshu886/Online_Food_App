package com.himanshu.model;

import java.util.ArrayList;
import java.util.List;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.himanshu.dto.RestaurantDto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
	
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private Long id;
		
		private String fullName;
		
		private String email;
		
		@JsonProperty(access = Access.WRITE_ONLY)
		private String password;
		
		private USER_ROLE role;
		
		@JsonIgnore   //bcoz whenever we fetch user that time we dont need orders list.
		@OneToMany(cascade = CascadeType.ALL, mappedBy ="customer")		//for fetching all orders we will write separate api
		private List<Order>  orders = new ArrayList<>();
		
		@ElementCollection
		private List<RestaurantDto> favorites = new ArrayList<>();
		
		@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
		private List<Address> addressess = new ArrayList<>();
		// whenever user order any food and provide his address, then we are gonna store his
		//adddress in user object, so that later we can use it. everytime he is ordering food
		//he dont need to provide same address again.
		
}
