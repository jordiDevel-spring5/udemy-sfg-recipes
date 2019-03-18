package guru.springframework.sfgrecipes.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import guru.springframework.sfgrecipes.commands.RecipeCommand;
import guru.springframework.sfgrecipes.domain.Recipe;

@Component
public class RecipeCommandToRecipe implements Converter<RecipeCommand, Recipe> {

	private final CategoryCommandToCategory categoryConveter;
    private final IngredientCommandToIngredient ingredientConverter;
    private final NotesCommandToNotes notesConverter;
	
	public RecipeCommandToRecipe(CategoryCommandToCategory categoryConveter,
			IngredientCommandToIngredient ingredientConverter, NotesCommandToNotes notesConverter) {
		super();
		this.categoryConveter = categoryConveter;
		this.ingredientConverter = ingredientConverter;
		this.notesConverter = notesConverter;
	}

	@Override
	public Recipe convert(RecipeCommand source) {
		if (source == null) {
			return null;
		}
		
		final Recipe recipe = new Recipe();
		recipe.setId(source.getId());
		recipe.setCookTime(source.getCookTime());
		recipe.setPrepTime(source.getPrepTime());
		recipe.setDescription(source.getDescription());
		recipe.setDifficulty(source.getDifficulty());
		recipe.setDirections(source.getDirections());
		recipe.setServings(source.getServings());
		recipe.setSource(source.getSource());
		recipe.setUrl(source.getUrl());
		
		if (source.getNotes() != null) {
			recipe.setNotes(this.notesConverter.convert(source.getNotes()));
		}
		
		if (source.getCategories() != null && !source.getCategories().isEmpty()) {
			source.getCategories().forEach(cat -> recipe.getCategories().add(this.categoryConveter.convert(cat)));
		}
		
		if (source.getIngredients() != null && !source.getIngredients().isEmpty()) {
			source.getIngredients().forEach(ing -> recipe.getIngredients().add(this.ingredientConverter.convert(ing)));
		}
		
		return recipe;
	}

}
