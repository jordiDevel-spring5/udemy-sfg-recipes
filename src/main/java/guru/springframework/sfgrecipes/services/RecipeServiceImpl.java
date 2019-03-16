package guru.springframework.sfgrecipes.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

import guru.springframework.sfgrecipes.domain.Recipe;
import guru.springframework.sfgrecipes.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {

	private final RecipeRepository recipeRepository;

	public RecipeServiceImpl(RecipeRepository recipeRepository) {
		this.recipeRepository = recipeRepository;
	}

	@Override
	public Recipe saveRecipe(Recipe recipe) {
		log.debug("[RecipeService] - saveRecipe has been called");
		
		return this.recipeRepository.save(recipe);
	}

	@Override
	public Set<Recipe> findAll() {
		log.debug("[RecipeService] - findAll has been called");
		
		Set<Recipe> recipeSet = new HashSet<>();
		
		this.recipeRepository.findAll().forEach(recipeSet::add);
		
		return recipeSet;
	}

	@Override
	public Recipe findById(Long id) {
		log.debug("[RecipeService] - findById has been called");

		return this.recipeRepository.findById(id).orElse(null);
	}
	
}
