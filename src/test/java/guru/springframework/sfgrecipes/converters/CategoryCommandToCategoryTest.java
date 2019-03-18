package guru.springframework.sfgrecipes.converters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import guru.springframework.sfgrecipes.commands.CategoryCommand;
import guru.springframework.sfgrecipes.domain.Category;

public class CategoryCommandToCategoryTest {

	public static final String DESCRIPTION = "description";
	public static final Long LONG_VALUE = 1L;
	
	CategoryCommandToCategory converter;
	
	@Before
	public void setUp() throws Exception {
		this.converter = new CategoryCommandToCategory();
	}

	@Test
	public void testNullParameter() throws Exception {
		assertNull(this.converter.convert(null));
	}

	@Test
	public void testEmptyObject() throws Exception {
		assertNotNull(this.converter.convert(new CategoryCommand()));
	}
	
	@Test
	public void testConvert() {
		//given
		CategoryCommand categoryCmd = new CategoryCommand();
		categoryCmd.setId(LONG_VALUE);
		categoryCmd.setDescription(DESCRIPTION);
		
		//when
		Category category = this.converter.convert(categoryCmd);
		
		//then
		assertNotNull(category);
		assertEquals(LONG_VALUE, category.getId());
		assertEquals(DESCRIPTION, category.getDescription());
	}

}
