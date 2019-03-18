package guru.springframework.sfgrecipes.converters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import guru.springframework.sfgrecipes.commands.UnitOfMeasureCommand;
import guru.springframework.sfgrecipes.domain.UnitOfMeasure;

public class UnitOfMeasureToUnitOfMeasureCommandTest {

	public static final String DESCRIPTION = "description";
	public static final Long LONG_VALUE = 1L;
	
	UnitOfMeasureToUnitOfMeasureCommand converter;
	
	@Before
	public void setUp() throws Exception {
		this.converter = new UnitOfMeasureToUnitOfMeasureCommand();
	}

	@Test
	public void testNullParameter() throws Exception {
		assertNull(this.converter.convert(null));
	}

	@Test
	public void testEmptyObject() throws Exception {
		assertNotNull(this.converter.convert(new UnitOfMeasure()));
	}
	
	@Test
	public void testConvert() {
		//given
		UnitOfMeasure uom = new UnitOfMeasure();
		uom.setId(LONG_VALUE);
		uom.setDescription(DESCRIPTION);
		
		//when
		UnitOfMeasureCommand uomCmd = this.converter.convert(uom);
		
		//then
		assertNotNull(uomCmd);
		assertEquals(LONG_VALUE, uomCmd.getId());
		assertEquals(DESCRIPTION, uomCmd.getDescription());
	}

}
