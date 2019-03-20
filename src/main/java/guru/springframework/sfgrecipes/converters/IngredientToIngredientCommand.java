package guru.springframework.sfgrecipes.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import guru.springframework.sfgrecipes.commands.IngredientCommand;
import guru.springframework.sfgrecipes.domain.Ingredient;
import lombok.Synchronized;

@Component
public class IngredientToIngredientCommand implements Converter<Ingredient, IngredientCommand> {

	private final UnitOfMeasureToUnitOfMeasureCommand uomConverter;
	
	public IngredientToIngredientCommand(UnitOfMeasureToUnitOfMeasureCommand uomConverter) {
		super();
		this.uomConverter = uomConverter;
	}

	@Synchronized
	@Nullable
	@Override
	public IngredientCommand convert(Ingredient source) {
		if (source == null) {
			return null;
		}
		
		final IngredientCommand ingredientCmd = new IngredientCommand();
		ingredientCmd.setId(source.getId());
		if (source.getRecipe() != null) {
			ingredientCmd.setRecipeId(source.getRecipe().getId());
		}
		ingredientCmd.setDescription(source.getDescription());
		ingredientCmd.setAmount(source.getAmount());
		ingredientCmd.setUom(this.uomConverter.convert(source.getUom()));
		return ingredientCmd;
	}

}
