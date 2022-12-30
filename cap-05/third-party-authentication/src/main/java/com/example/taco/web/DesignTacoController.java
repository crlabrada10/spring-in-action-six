/**
 * 
 */
package com.example.taco.web;

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
import com.example.taco.data.IngredientRepository;

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

	@Autowired
	public DesignTacoController(IngredientRepository ingredientRepo) {
		this.ingredientRepo = ingredientRepo;
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

	@GetMapping
	public String showDesignForm() {
		return "desing";
	}

	@PostMapping
	public String processTaco(@Valid Taco taco, Errors errors, @ModelAttribute TacoOrder tacoOrder) {
		if (errors.hasErrors()) {
			return "desing";
		}
		tacoOrder.addTaco(taco);
		log.info("Processing taco {} ", taco);
		return "redirect:/orders/current";
	}

	private Iterable<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
		return ingredients.stream().filter(x -> x.getType().equals(type)).collect(Collectors.toList());
	}
}
