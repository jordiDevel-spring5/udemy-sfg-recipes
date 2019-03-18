package guru.springframework.sfgrecipes.converters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import guru.springframework.sfgrecipes.commands.RecipeCommand;
import guru.springframework.sfgrecipes.domain.Recipe;

public class RecipeCommandToRecipeTest {

	public static final String DESCRIPTION = "description";
	public static final Long LONG_VALUE = 1L;
	
	RecipeCommandToRecipe converter;
	
	@Before
	public void setUp() throws Exception {
		this.converter = new RecipeCommandToRecipe(
			new CategoryCommandToCategory(), 
			new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure()), 
			new NotesCommandToNotes()
		);
	}

	@Test
	public void testNullParameter() throws Exception {
		assertNull(this.converter.convert(null));
	}

	@Test
	public void testEmptyObject() throws Exception {
		assertNotNull(this.converter.convert(new RecipeCommand()));
	}
	
	@Test
	public void testConvert() {
		//given
		RecipeCommand recipeCmd = new RecipeCommand();
		recipeCmd.setId(LONG_VALUE);
		recipeCmd.setDescription(DESCRIPTION);
		
		//when
		Recipe recipe = this.converter.convert(recipeCmd);
		
		//then
		assertNotNull(recipe);
		assertEquals(LONG_VALUE, recipe.getId());
		assertEquals(DESCRIPTION, recipe.getDescription());
	}

}
