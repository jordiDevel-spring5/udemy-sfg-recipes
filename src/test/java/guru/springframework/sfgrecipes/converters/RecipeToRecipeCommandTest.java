package guru.springframework.sfgrecipes.converters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import guru.springframework.sfgrecipes.commands.RecipeCommand;
import guru.springframework.sfgrecipes.domain.Recipe;

public class RecipeToRecipeCommandTest {

	public static final String DESCRIPTION = "description";
	public static final Long LONG_VALUE = 1L;
	
	RecipeToRecipeCommand converter;
	
	@Before
	public void setUp() throws Exception {
		this.converter = new RecipeToRecipeCommand(
			new CategoryToCategoryCommand(), 
			new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand()), 
			new NotesToNotesCommand()
		);
	}

	@Test
	public void testNullParameter() throws Exception {
		assertNull(this.converter.convert(null));
	}

	@Test
	public void testEmptyObject() throws Exception {
		assertNotNull(this.converter.convert(new Recipe()));
	}
	
	@Test
	public void testConvert() {
		//given
		Recipe recipe = new Recipe();
		recipe.setId(LONG_VALUE);
		recipe.setDescription(DESCRIPTION);
		
		//when
		RecipeCommand recipeCmd = this.converter.convert(recipe);
		
		//then
		assertNotNull(recipeCmd);
		assertEquals(LONG_VALUE, recipeCmd.getId());
		assertEquals(DESCRIPTION, recipeCmd.getDescription());
	}

}
