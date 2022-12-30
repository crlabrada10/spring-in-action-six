/**
 * 
 */
package com.example.taco.web;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.example.taco.TacoOrder;
import com.example.taco.data.OrderRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * @author clabrada
 *
 */
@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes(names = "tacoOrder")
public class OrderController {

	private OrderRepository orderRepository;

	public OrderController(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}

	@RequestMapping("/current")
	public String orderForm() {
		return "orderForm";
	}

	@PostMapping
	public String processOrder(@Valid TacoOrder order, Errors erros, SessionStatus sessionStatus) {

		if (erros.hasErrors()) {
			return "orderForm";
		}
		log.info("Order submited {} ", order);
		orderRepository.save(order);
		sessionStatus.setComplete();
		return "redirect:/";
	}
}
