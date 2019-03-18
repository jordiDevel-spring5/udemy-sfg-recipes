package guru.springframework.sfgrecipes.converters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import guru.springframework.sfgrecipes.commands.CategoryCommand;
import guru.springframework.sfgrecipes.domain.Category;

public class CategoryToCategoryCommandTest {

	public static final String DESCRIPTION = "description";
	public static final Long LONG_VALUE = 1L;
	
	CategoryToCategoryCommand converter;
	
	@Before
	public void setUp() throws Exception {
		this.converter = new CategoryToCategoryCommand();
	}

	@Test
	public void testNullParameter() throws Exception {
		assertNull(this.converter.convert(null));
	}

	@Test
	public void testEmptyObject() throws Exception {
		assertNotNull(this.converter.convert(new Category()));
	}
	
	@Test
	public void testConvert() {
		//given
		Category category = new Category();
		category.setId(LONG_VALUE);
		category.setDescription(DESCRIPTION);
		
		//when
		CategoryCommand categoryCmd = this.converter.convert(category);
		
		//then
		assertNotNull(categoryCmd);
		assertEquals(LONG_VALUE, categoryCmd.getId());
		assertEquals(DESCRIPTION, categoryCmd.getDescription());
	}

}
