package guru.springframework.sfgrecipes.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import guru.springframework.sfgrecipes.commands.RecipeCommand;
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
	
	@GetMapping("/{id}/show")
	public String getRecipeById(@PathVariable String id, Model model) {
		log.debug("[RecipeController] - getRecipeById has been called");
		
		model.addAttribute("recipe", this.recipeService.findCommandById(Long.valueOf(id)));
		
		return "recipe/show";
	}
	
	@GetMapping("/new")
	public String newRecipe(Model model) {
		log.debug("[RecipeController] - newRecipe has been called");
		
		model.addAttribute("recipe", new RecipeCommand());
		
		return "recipe/recipeform";
	}
	
	@GetMapping("/{id}/update")
	public String updateRecipe(@PathVariable String id, Model model) {
		log.debug("[RecipeController] - updateRecipe has been called");
		
		model.addAttribute("recipe", this.recipeService.findCommandById(Long.valueOf(id)));
		
		return "recipe/recipeform";
	}
	
	@GetMapping("/{id}/delete")
	public String deleteRecipe(@PathVariable String id) {
		log.debug("[RecipeController] - deleteRecipe has been called");
		
		this.recipeService.deleteById(Long.valueOf(id));
		
		return "redirect:/";
	}
	
	@PostMapping
	public String saveOrUpdate(@ModelAttribute RecipeCommand command) {
		log.debug("[RecipeController] - saveOrUpdate has been called");
		
		RecipeCommand savedCommand = this.recipeService.saveRecipeCommand(command);
		
		return "redirect:/recipe/" + savedCommand.getId() + "/show";
	}
}
