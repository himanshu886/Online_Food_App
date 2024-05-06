package com.himanshu.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.himanshu.model.Address;
import com.himanshu.model.Cart;
import com.himanshu.model.CartItem;
import com.himanshu.model.Order;
import com.himanshu.model.OrderItem;
import com.himanshu.model.Restaurant;
import com.himanshu.model.User;
import com.himanshu.repository.AddressRepository;
import com.himanshu.repository.OrderItemRepository;
import com.himanshu.repository.OrderRepository;
import com.himanshu.repository.UserRepository;
import com.himanshu.request.OrderRequest;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RestaurantService restaurantService;
	
	@Autowired
	private CartService cartService;
		
	@Override
	public Order createOrder(OrderRequest order, User user) throws Exception {
		
		  Address shippAddress = order.getDeliveryAddress();
		  
		  Address savedAddress = addressRepository.save(shippAddress);
		  
		  if(!user.getAddressess().contains(savedAddress)) {
			  user.getAddressess().add(savedAddress);
			  userRepository.save(user);
		  }
		  
		  Restaurant restaurant = restaurantService.findRestaurantById(order.getRestaurantId());
		  
		  Order createdOrder = new Order();
		  createdOrder.setCustomer(user);
		  createdOrder.setCreatedDate(new Date());
		  createdOrder.setOrderStatus("PENDING");
		  createdOrder.setDeliveryAddress(savedAddress);
		  createdOrder.setRestaurant(restaurant);
		  
		  Cart cart = cartService.findCartByUserId(user.getId());
		  
		  List<OrderItem> orderItems = new ArrayList<>();
		  
		  for(CartItem cartItem : cart.getItem()) {
			  OrderItem orderItem = new OrderItem();
			  orderItem.setFood(cartItem.getFood());
			  orderItem.setIngredients(cartItem.getIngredients());
			  orderItem.setQuantity(cartItem.getQuantity());
			  orderItem.setTotalPrice(cartItem.getTotalPrice());
			  
			  OrderItem savedOrderItem = orderItemRepository.save(orderItem);
			  orderItems.add(savedOrderItem);
		  }
		  
		  Long totalPrice = cartService.calculateCartTotals(cart);
		  
		  createdOrder.setItem(orderItems);
		  createdOrder.setTotalPrice(cart.getTotal());
		  
		  Order savedOrder = orderRepository.save(createdOrder);
		  restaurant.getOrders().add(savedOrder);
		  
		  return createdOrder;
		  
	}

	@Override
	public Order updateOrder(Long orderId, String orderStatus) throws Exception {
		
		Order order = findOrderById(orderId);
		
		if(orderStatus.equals("OUT_FOR_DELIVERY") 
				 || orderStatus.equals("OUT_FOR_DELIVERY")
				 || orderStatus.equals("OUT_FOR_DELIVERY") 
				 || orderStatus.equals("OUT_FOR_DELIVERY") 
		 ) {
			order.setOrderStatus(orderStatus);
			return orderRepository.save(order);
		}
		
		throw new Exception("Please select a valid order status");
	}

	@Override
	public void cancelOrder(Long orderId) throws Exception {

		Order order = findOrderById(orderId);
		orderRepository.deleteById(orderId);
	}

	@Override
	public List<Order> getUsersOrder(Long userId) throws Exception {
		return orderRepository.findByCustomerId(userId);
	}

	@Override
	public List<Order> getRestaurantsOrder(Long restaurantId, String orderStatus) throws Exception {
		List<Order> orders = orderRepository.findByRestaurantId(restaurantId);
		
		if(orderStatus != null) {
			orders.stream().filter(order->
			order.getOrderStatus().equals(orderStatus)).collect(Collectors.toList());
		}
		return orders;
	}

	@Override
	public Order findOrderById(Long orderid) throws Exception {
		
		Optional<Order> optionalOrder = orderRepository.findById(orderid);
		
		if(optionalOrder.isEmpty()) {
			throw new Exception("order not found");
		}
	return optionalOrder.get();
	
	}
}
