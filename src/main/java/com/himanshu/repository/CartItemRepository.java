package com.himanshu.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.himanshu.model.Cart;
import com.himanshu.model.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
	
		
}
