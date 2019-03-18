package guru.springframework.sfgrecipes.converters;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import guru.springframework.sfgrecipes.commands.UnitOfMeasureCommand;
import guru.springframework.sfgrecipes.domain.UnitOfMeasure;

public class UnitOfMeasureCommandToUnitOfMeasureTest {

	public static final String DESCRIPTION = "description";
	public static final Long LONG_VALUE = 1L;
	
	UnitOfMeasureCommandToUnitOfMeasure converter;
	
	@Before
	public void setUp() throws Exception {
		this.converter = new UnitOfMeasureCommandToUnitOfMeasure();
	}

	@Test
	public void testNullParameter() throws Exception {
		assertNull(this.converter.convert(null));
	}

	@Test
	public void testEmptyObject() throws Exception {
		assertNotNull(this.converter.convert(new UnitOfMeasureCommand()));
	}
	
	@Test
	public void testConvert() {
		//given
		UnitOfMeasureCommand uomCmd = new UnitOfMeasureCommand();
		uomCmd.setId(LONG_VALUE);
		uomCmd.setDescription(DESCRIPTION);
		
		//when
		UnitOfMeasure uom = this.converter.convert(uomCmd);
		
		//then
		assertNotNull(uom);
		assertEquals(LONG_VALUE, uom.getId());
		assertEquals(DESCRIPTION, uom.getDescription());
	}

}
