package guru.springframework.sfgrecipes.converters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import guru.springframework.sfgrecipes.commands.IngredientCommand;
import guru.springframework.sfgrecipes.commands.UnitOfMeasureCommand;
import guru.springframework.sfgrecipes.domain.Ingredient;

public class IngredientCommandToIngredientTest {

	public static final String DESCRIPTION = "description";
	public static final Long LONG_VALUE = 1L;
	public static final BigDecimal BIGDECIMAL_VALUE = new BigDecimal(2.5);
	
	IngredientCommandToIngredient converter;
	UnitOfMeasureCommandToUnitOfMeasure uomConverter;
	
	@Before
	public void setUp() throws Exception {
		this.uomConverter = new UnitOfMeasureCommandToUnitOfMeasure();
		this.converter = new IngredientCommandToIngredient(uomConverter);
	}

	@Test
	public void testNullParameter() throws Exception {
		assertNull(this.converter.convert(null));
	}

	@Test
	public void testEmptyObject() throws Exception {
		assertNotNull(this.converter.convert(new IngredientCommand()));
	}
	
	@Test
	public void testConvert() {
		//given
		UnitOfMeasureCommand uomCmd = new UnitOfMeasureCommand();
		uomCmd.setId(LONG_VALUE);
		uomCmd.setDescription(DESCRIPTION);
		
		IngredientCommand ingredientCmd = new IngredientCommand();
		ingredientCmd.setId(LONG_VALUE);
		ingredientCmd.setDescription(DESCRIPTION);
		ingredientCmd.setAmount(BIGDECIMAL_VALUE);
		ingredientCmd.setUom(uomCmd);
		
		//when
		Ingredient ingredient = this.converter.convert(ingredientCmd);
		
		//then
		assertNotNull(ingredientCmd);
		assertEquals(LONG_VALUE, ingredientCmd.getId());
		assertEquals(DESCRIPTION, ingredientCmd.getDescription());
		assertEquals(BIGDECIMAL_VALUE, ingredient.getAmount());
		assertEquals(LONG_VALUE, ingredient.getUom().getId());
		assertEquals(DESCRIPTION, ingredientCmd.getUom().getDescription());
	}

}
