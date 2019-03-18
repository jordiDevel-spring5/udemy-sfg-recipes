package guru.springframework.sfgrecipes.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import guru.springframework.sfgrecipes.commands.RecipeCommand;
import guru.springframework.sfgrecipes.converters.RecipeCommandToRecipe;
import guru.springframework.sfgrecipes.converters.RecipeToRecipeCommand;
import guru.springframework.sfgrecipes.domain.Recipe;
import guru.springframework.sfgrecipes.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {

	private final RecipeRepository recipeRepository;
	private final RecipeCommandToRecipe recipeCommandToRecipe;
    private final RecipeToRecipeCommand recipeToRecipeCommand;

	public RecipeServiceImpl(RecipeRepository recipeRepository, RecipeCommandToRecipe recipeCommandToRecipe,
			RecipeToRecipeCommand recipeToRecipeCommand) {
		this.recipeRepository = recipeRepository;
		this.recipeCommandToRecipe = recipeCommandToRecipe;
		this.recipeToRecipeCommand = recipeToRecipeCommand;
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

	@Override
	@Transactional
	public RecipeCommand saveRecipeCommand(RecipeCommand recipeCmd) {
		log.debug("[RecipeService] - saveRecipeCommand has been called");
		
		Recipe detachedRecipe = this.recipeCommandToRecipe.convert(recipeCmd);

        Recipe savedRecipe = this.recipeRepository.save(detachedRecipe);
        log.debug("Saved RecipeId:" + savedRecipe.getId());
        return this.recipeToRecipeCommand.convert(savedRecipe);
	}

	@Override
	@Transactional
	public RecipeCommand findCommandById(Long id) {
		log.debug("[RecipeService] - findCommandById has been called");

		Recipe recipe = this.recipeRepository.findById(id).orElse(null);
		
		return this.recipeToRecipeCommand.convert(recipe);
	}
	
}
