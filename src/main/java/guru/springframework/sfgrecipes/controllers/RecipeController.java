package guru.springframework.sfgrecipes.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import guru.springframework.sfgrecipes.services.RecipeService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/recipe")
public class RecipeController {

	private final RecipeService recipeService;
	
	public RecipeController(RecipeService recipeService) {
		this.recipeService = recipeService;
	}
	
	@RequestMapping("/show/{id}")
	public String getRecipeById(@PathVariable String id, Model model)
	{
		log.debug("[RecipeController] - getRecipeById has been called");
		
		model.addAttribute("recipe", this.recipeService.findById(Long.valueOf(id)));
		
		return "recipe/show";
	}
}
