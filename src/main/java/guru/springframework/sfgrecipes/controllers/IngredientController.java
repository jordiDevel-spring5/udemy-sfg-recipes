package guru.springframework.sfgrecipes.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import guru.springframework.sfgrecipes.commands.IngredientCommand;
import guru.springframework.sfgrecipes.commands.RecipeCommand;
import guru.springframework.sfgrecipes.commands.UnitOfMeasureCommand;
import guru.springframework.sfgrecipes.services.IngredientService;
import guru.springframework.sfgrecipes.services.RecipeService;
import guru.springframework.sfgrecipes.services.UnitOfMeasureService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class IngredientController {

	private final RecipeService recipeService;
	private final IngredientService ingredientService;
	private final UnitOfMeasureService unitOfMeasureService;

	public IngredientController(RecipeService recipeService, IngredientService ingredientService,
			UnitOfMeasureService unitOfMeasureService) {
		this.recipeService = recipeService;
		this.ingredientService = ingredientService;
		this.unitOfMeasureService = unitOfMeasureService;
	}
	
	@GetMapping("/recipe/{recipeId}/ingredients")
	public String listIngredients(@PathVariable String recipeId, Model model) {
		log.debug("[IngredientController] - listIngredients has been called");
		
		model.addAttribute("recipe", this.recipeService.findCommandById(Long.valueOf(recipeId)));
		
		return "recipe/ingredient/list";
	}
	
	@GetMapping("/recipe/{recipeId}/ingredient/{id}/show")
	public String showRecipeIngredient(@PathVariable String recipeId, @PathVariable String id, Model model) {
		log.debug("[IngredientController] - getIngredientByRecipeIdAndId has been called");
		
		model.addAttribute("ingredient", this.ingredientService.findCommandByRecipeIdAndId(Long.valueOf(recipeId), Long.valueOf(id)));
		
		return "recipe/ingredient/show";
	}
	
	@GetMapping("/recipe/{recipeId}/ingredient/new")
	public String newRecipeIngredient(@PathVariable String recipeId, Model model) {
		log.debug("[IngredientController] - newRecipeIngredient has been called");
		
		RecipeCommand recipeCmd = this.recipeService.findCommandById(Long.valueOf(recipeId));
		
		IngredientCommand ingredientCmd = new IngredientCommand();
		ingredientCmd.setRecipeId(Long.valueOf(recipeCmd.getId()));
		ingredientCmd.setUom(new UnitOfMeasureCommand());
		
		model.addAttribute("ingredient", ingredientCmd);
		
		model.addAttribute("uomList", this.unitOfMeasureService.listAllUoms());
		
		return "recipe/ingredient/ingredientform";
	}
	
	@GetMapping("/recipe/{recipeId}/ingredient/{id}/update")
	public String updateRecipeIngredient(@PathVariable String recipeId, @PathVariable String id, Model model) {
		log.debug("[IngredientController] - updateRecipeIngredient has been called");
		
		model.addAttribute("ingredient", this.ingredientService.findCommandByRecipeIdAndId(Long.valueOf(recipeId), Long.valueOf(id)));
		
		model.addAttribute("uomList", this.unitOfMeasureService.listAllUoms());
		
		return "recipe/ingredient/ingredientform";
	}
	
	@PostMapping("/recipe/{recipeId}/ingredient")
	public String saveOrUpdate(@ModelAttribute IngredientCommand ingredientCmd) {
		log.debug("[IngredientController] - saveOrUpdate has been called");
		
		IngredientCommand savedCommand = this.ingredientService.saveIngredientCommand(ingredientCmd);
		
		return "redirect:/recipe/" + savedCommand.getRecipeId() + "/ingredient/" + savedCommand.getId() + "/show";
	}
	
	@GetMapping("/recipe/{recipeId}/ingredient/{id}/delete")
	public String deleteRecipeIngredient(@PathVariable String recipeId, @PathVariable String id, Model model) {
		log.debug("[IngredientController] - deleteRecipeIngredient has been called");
		
		this.ingredientService.deleteById(Long.valueOf(recipeId), Long.valueOf(id));
		
		return "redirect:/recipe/" + recipeId + "/ingredients";
	}
}
