/**
 * 
 */
package com.example.taco.web;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.taco.Ingredient;
import com.example.taco.Ingredient.Type;
import com.example.taco.Taco;
import com.example.taco.TacoOrder;
import com.example.taco.User;
import com.example.taco.data.IngredientRepository;
import com.example.taco.data.UserRepository;
import com.example.taco.data.service.OrderAdminService;

import lombok.extern.slf4j.Slf4j;



/**
 * @author clabrada
 *
 */
@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes(names = "tacoOrder")
public class DesignTacoController {

	private final IngredientRepository ingredientRepo;
	private UserRepository userRepo;
	
	//Test para probar seguridad a nivel de metodo
	//private OrderAdminService orderAdminService;
	
	@Autowired
	public DesignTacoController(IngredientRepository ingredientRepo, UserRepository userRepo, OrderAdminService orderAdminService) {
		this.ingredientRepo = ingredientRepo;
		this.userRepo = userRepo;
		//this.orderAdminService = orderAdminService;
	}
	
	@ModelAttribute
	public void addIngredientsToModel(Model model) {
		List<Ingredient> ingredients = new ArrayList<>();
		ingredientRepo.findAll().forEach(i -> ingredients.add(i));

		Type[] types = Ingredient.Type.values();
		for (Type type : types) {
			model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
		}
	}

	@ModelAttribute(name = "tacoOrder")
	public TacoOrder order() {
		return new TacoOrder();
	}

	@ModelAttribute(name = "taco")
	public Taco taco() {
		return new Taco();
	}
	
	  @ModelAttribute(name = "user")
	  public User user(Principal principal) {
		    String username = principal.getName();
		    User user = userRepo.findByUsername(username);
		    return user;
	  }
	  
	@GetMapping
	public String showDesignForm() {
		return "desing";
	}

	@PostMapping
	public String processTaco(@Valid Taco taco, Errors errors, @ModelAttribute TacoOrder tacoOrder) {
		log.info("----- taco {} ");
		if (errors.hasErrors()) {
			return "desing";
		}
		tacoOrder.addTaco(taco);
		log.info("Processing taco {} ", taco);
		//orderAdminService.deleteAllOrders();
		return "redirect:/orders/current";
	}

	private Iterable<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
		return ingredients.stream().filter(x -> x.getType().equals(type)).collect(Collectors.toList());
	}
}
