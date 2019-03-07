package guru.springframework.sfgrecipes.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

import guru.springframework.sfgrecipes.domain.Recipe;
import guru.springframework.sfgrecipes.repositories.RecipeRepository;

@Service
public class RecipeServiceImpl implements RecipeService {

	private final RecipeRepository recipeRepository;

	public RecipeServiceImpl(RecipeRepository recipeRepository) {
		this.recipeRepository = recipeRepository;
	}

	@Override
	public Recipe saveRecipe(Recipe recipe) {
		return this.recipeRepository.save(recipe);
	}

	@Override
	public Set<Recipe> findAll() {
		Set<Recipe> recipeSet = new HashSet<>();
		
		this.recipeRepository.findAll().forEach(recipeSet::add);
		
		return recipeSet;
	}
	
}
