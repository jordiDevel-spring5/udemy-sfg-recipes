package guru.springframework.sfgrecipes.services;

import java.util.Set;

import guru.springframework.sfgrecipes.domain.Recipe;

public interface RecipeService {

	Recipe saveRecipe(Recipe recipe);
	
	Set<Recipe> findAll();
}
