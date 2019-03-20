package guru.springframework.sfgrecipes.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import guru.springframework.sfgrecipes.commands.RecipeCommand;
import guru.springframework.sfgrecipes.domain.Recipe;
import lombok.Synchronized;

@Component
public class RecipeToRecipeCommand implements Converter<Recipe, RecipeCommand> {

	private final CategoryToCategoryCommand categoryConveter;
    private final IngredientToIngredientCommand ingredientConverter;
    private final NotesToNotesCommand notesConverter;
    
	public RecipeToRecipeCommand(CategoryToCategoryCommand categoryConveter,
			IngredientToIngredientCommand ingredientConverter, NotesToNotesCommand notesConverter) {
		super();
		this.categoryConveter = categoryConveter;
		this.ingredientConverter = ingredientConverter;
		this.notesConverter = notesConverter;
	}

	@Synchronized
	@Nullable
	@Override
	public RecipeCommand convert(Recipe source) {
		if (source == null) {
			return null;
		}
		
		final RecipeCommand recipeCmd = new RecipeCommand();
		recipeCmd.setId(source.getId());
		recipeCmd.setCookTime(source.getCookTime());
		recipeCmd.setPrepTime(source.getPrepTime());
		recipeCmd.setDescription(source.getDescription());
		recipeCmd.setDifficulty(source.getDifficulty());
		recipeCmd.setDirections(source.getDirections());
		recipeCmd.setServings(source.getServings());
		recipeCmd.setSource(source.getSource());
		recipeCmd.setUrl(source.getUrl());
		recipeCmd.setNotes(this.notesConverter.convert(source.getNotes()));
		
		if (source.getNotes() != null) {
			recipeCmd.setNotes(this.notesConverter.convert(source.getNotes()));
		}
		
		if (source.getCategories() != null && !source.getCategories().isEmpty()) {
			source.getCategories().forEach(cat -> recipeCmd.getCategories().add(this.categoryConveter.convert(cat)));
		}
		
		if (source.getIngredients() != null && !source.getIngredients().isEmpty()) {
			source.getIngredients().forEach(ing -> recipeCmd.getIngredients().add(this.ingredientConverter.convert(ing)));
		}
		
		return recipeCmd;
	}

}
