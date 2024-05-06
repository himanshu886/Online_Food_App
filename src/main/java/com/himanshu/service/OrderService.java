package com.himanshu.service;

import java.util.List;

import com.himanshu.model.Order;
import com.himanshu.model.User;
import com.himanshu.request.OrderRequest;

public interface OrderService {
	
		public Order createOrder(OrderRequest order, User user) throws Exception;
		
		public Order updateOrder(Long orderId, String orderStatus) throws Exception;
		
		public void cancelOrder(Long orderId) throws Exception;
		
		public List<Order> getUsersOrder(Long userId) throws Exception;
		
		public List<Order> getRestaurantsOrder(Long restaurantId, String orderStatus) throws Exception;
		
		public Order findOrderById(Long orderid) throws Exception;
}
