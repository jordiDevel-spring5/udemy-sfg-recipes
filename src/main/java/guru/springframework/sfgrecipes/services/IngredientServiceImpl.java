package guru.springframework.sfgrecipes.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import guru.springframework.sfgrecipes.commands.IngredientCommand;
import guru.springframework.sfgrecipes.converters.IngredientCommandToIngredient;
import guru.springframework.sfgrecipes.converters.IngredientToIngredientCommand;
import guru.springframework.sfgrecipes.domain.Ingredient;
import guru.springframework.sfgrecipes.domain.Recipe;
import guru.springframework.sfgrecipes.repositories.RecipeRepository;
import guru.springframework.sfgrecipes.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService {

	private final RecipeRepository recipeRepository;
	private final UnitOfMeasureRepository unitOfMeasureRepository;
	private final IngredientToIngredientCommand ingredientToIngredientCommand;
	private final IngredientCommandToIngredient ingredientCommandToIngredient;
	
		public IngredientServiceImpl(RecipeRepository recipeRepository,
				UnitOfMeasureRepository unitOfMeasureRepository,
				IngredientToIngredientCommand ingredientToIngredientCommand,
				IngredientCommandToIngredient ingredientCommandToIngredient) {
		this.recipeRepository = recipeRepository;
		this.unitOfMeasureRepository = unitOfMeasureRepository;
		this.ingredientToIngredientCommand = ingredientToIngredientCommand;
		this.ingredientCommandToIngredient = ingredientCommandToIngredient;
	}

	@Override
	public IngredientCommand findCommandByRecipeIdAndId(Long recipeId, Long ingredientId) {
		log.debug("[IngredientService] - findCommandByRecipeIdAndId has been called");

		Optional<Recipe> recipeOptional = this.recipeRepository.findById(recipeId);

        if (!recipeOptional.isPresent()) {
            //todo impl error handling
            log.error("recipe id not found. Id: " + recipeId);
        }

        Recipe recipe = recipeOptional.get();

        Optional<IngredientCommand> ingredientCommandOptional = recipe.getIngredients().stream()
                .filter(ingredient -> ingredient.getId().equals(ingredientId))
                .map(ingredient -> this.ingredientToIngredientCommand.convert(ingredient)).findFirst();

        if (!ingredientCommandOptional.isPresent()) {
            //todo impl error handling
            log.error("Ingredient id not found: " + ingredientId);
        }

        return ingredientCommandOptional.get();
	}

	@Override
	public IngredientCommand saveIngredientCommand(IngredientCommand ingredientCmd) {
		log.debug("[IngredientService] - saveIngredientCommand has been called");
		
		Recipe recipe = this.recipeRepository.findById(ingredientCmd.getRecipeId()).orElse(null);
		if (recipe == null) {
			log.error("Recipe not found for id: " + ingredientCmd.getRecipeId());
			
			return new IngredientCommand();
		}
		
		Ingredient recipeIngredient = recipe.getIngredients().stream()
			.filter(ingredient -> ingredient.getId().equals(ingredientCmd.getId()))
			.findFirst().orElse(null);
		
		if (recipeIngredient == null) {
			//it's a new ingredient, add it to recipe ingredients list
			recipe.addIngredient(this.ingredientCommandToIngredient.convert(ingredientCmd));
		}
		else {
			//ingredient found, update it
			recipeIngredient.setDescription(ingredientCmd.getDescription());
			recipeIngredient.setAmount(ingredientCmd.getAmount());
			recipeIngredient.setUom(unitOfMeasureRepository
                    .findById(ingredientCmd.getUom().getId())
                    .orElseThrow(() -> new RuntimeException("UOM NOT FOUND"))); //todo address this
		}
		
		Recipe savedRecipe = this.recipeRepository.save(recipe);
		
		Optional<Ingredient> savedIngredientOptional = savedRecipe.getIngredients().stream()
        	.filter(recipeIngredients -> recipeIngredients.getId().equals(ingredientCmd.getId()))
        	.findFirst();
        
		if (!savedIngredientOptional.isPresent()) {
			savedIngredientOptional = savedRecipe.getIngredients().stream()
	        	.filter(recipeIngredients -> recipeIngredients.getDescription().equals(ingredientCmd.getDescription()))
	        	.filter(recipeIngredients -> recipeIngredients.getAmount().equals(ingredientCmd.getAmount()))
	        	.filter(recipeIngredients -> recipeIngredients.getUom().getId().equals(ingredientCmd.getUom().getId()))
	        	.findFirst();
		}
		
		return this.ingredientToIngredientCommand.convert(savedIngredientOptional.get());
	}

	@Override
	public void deleteById(Long recipeId, Long ingredientId) {
		log.debug("[IngredientService] - deleteById has been called");
		
		Optional<Recipe> recipeOptional = this.recipeRepository.findById(recipeId);
		if (recipeOptional.isPresent()) {
			Recipe recipe = recipeOptional.get();
			Optional<Ingredient> ingredientOptional = recipe.getIngredients().stream()
				.filter(ingredient -> ingredient.getId().equals(ingredientId))
				.findFirst();
			
			if (ingredientOptional.isPresent()) {
				recipe.removeIngredient(ingredientOptional.get());
				
				this.recipeRepository.save(recipe);
			}
			else {
				log.debug("Ingredient not found for id: " + ingredientId);
			}
		}
		else {
			log.debug("Recipe not found for id: " + recipeId);
		}
	}

}
