package guru.springframework.sfgrecipes.services;

import java.util.Set;

import guru.springframework.sfgrecipes.commands.RecipeCommand;
import guru.springframework.sfgrecipes.domain.Recipe;

public interface RecipeService {

	Recipe saveRecipe(Recipe recipe);
	
	Set<Recipe> findAll();
	
	Recipe findById(Long id);
	
	RecipeCommand findCommandById(Long id);
	
	RecipeCommand saveRecipeCommand(RecipeCommand recipeCmd);
	
	void deleteById(Long id);
}
