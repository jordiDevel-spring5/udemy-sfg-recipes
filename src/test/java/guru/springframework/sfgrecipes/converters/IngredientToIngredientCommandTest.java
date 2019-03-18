package guru.springframework.sfgrecipes.converters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import guru.springframework.sfgrecipes.commands.IngredientCommand;
import guru.springframework.sfgrecipes.domain.Ingredient;
import guru.springframework.sfgrecipes.domain.UnitOfMeasure;

public class IngredientToIngredientCommandTest {

	public static final String DESCRIPTION = "description";
	public static final Long LONG_VALUE = 1L;
	public static final BigDecimal BIGDECIMAL_VALUE = new BigDecimal(2.5);
	
	IngredientToIngredientCommand converter;
	
	@Before
	public void setUp() throws Exception {
		this.converter = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
	}

	@Test
	public void testNullParameter() throws Exception {
		assertNull(this.converter.convert(null));
	}

	@Test
	public void testEmptyObject() throws Exception {
		assertNotNull(this.converter.convert(new Ingredient()));
	}
	
	@Test
	public void testConvert() {
		//given
		UnitOfMeasure uom = new UnitOfMeasure();
		uom.setId(LONG_VALUE);
		uom.setDescription(DESCRIPTION);
		
		Ingredient ingredient = new Ingredient();
		ingredient.setId(LONG_VALUE);
		ingredient.setDescription(DESCRIPTION);
		ingredient.setAmount(BIGDECIMAL_VALUE);
		ingredient.setUom(uom);
		
		//when
		IngredientCommand ingredientCmd = this.converter.convert(ingredient);
		
		//then
		assertNotNull(ingredientCmd);
		assertEquals(LONG_VALUE, ingredientCmd.getId());
		assertEquals(DESCRIPTION, ingredientCmd.getDescription());
		assertEquals(BIGDECIMAL_VALUE, ingredient.getAmount());
		assertEquals(LONG_VALUE, ingredient.getUom().getId());
		assertEquals(DESCRIPTION, ingredientCmd.getUom().getDescription());
	}

}
