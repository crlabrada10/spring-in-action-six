/**
 * 
 */
package tacos.web;

import javax.validation.Valid;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import lombok.extern.slf4j.Slf4j;
import tacos.TacoOrder;
import tacos.User;
import tacos.data.OrderRepository;

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
	private OrderProps props;

	public OrderController(OrderRepository orderRepository, OrderProps props) {
		this.orderRepository = orderRepository;
		this.props = props;
	}

	@RequestMapping("/current")
	public String orderForm() {
		return "orderForm";
	}

	@PostMapping
	public String processOrder(@Valid TacoOrder order, Errors erros, SessionStatus sessionStatus,
			@AuthenticationPrincipal User user) {

		if (erros.hasErrors()) {
			return "orderForm";
		}
		order.setUser(user);
		log.info("Order submited {} ", order);
		orderRepository.save(order);
		sessionStatus.setComplete();
		return "redirect:/";
	}

	@GetMapping
	public String ordersForUser(@AuthenticationPrincipal User user, Model model) {

		Pageable pageable = PageRequest.of(0, props.getPageSize());
		model.addAttribute("orders", orderRepository.findByUserOrderByPlacedAtDesc(user, pageable));
		return "orderList";
	}
}
