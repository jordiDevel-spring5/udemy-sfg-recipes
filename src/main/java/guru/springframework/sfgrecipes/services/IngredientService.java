package guru.springframework.sfgrecipes.services;

import guru.springframework.sfgrecipes.commands.IngredientCommand;

public interface IngredientService {

	IngredientCommand findCommandByRecipeIdAndId(Long recipeId, Long ingredientId);

	IngredientCommand saveIngredientCommand(IngredientCommand ingredientCmd);

	void deleteById(Long recipeId, Long ingredientId);

}
