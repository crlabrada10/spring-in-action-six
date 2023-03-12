/**
 * 
 */
package com.example.taco.data.service;

import java.util.Optional;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.example.taco.TacoOrder;
import com.example.taco.data.OrderRepository;

/**
 * @author clabrada
 *
 */
@Service
public class OrderAdminService {

	private OrderRepository orderRepository;

	public OrderAdminService(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	public void deleteAllOrders() {
		orderRepository.deleteAll();
	}
	
	@PostAuthorize("hasRole('ADMIN') || " +"returnObject.user.username == authentication.name")
	public TacoOrder getOrderById(long id) {
		 Optional<TacoOrder> tacoOrder= orderRepository.findById(id);
		 if(tacoOrder.isPresent()) {
			return  tacoOrder.get();
		 }
		return null;
		
	}
}
