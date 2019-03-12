package guru.springframework.sfgrecipes.domain;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class CategoryTest {

	Category category;
	
	@Before
	public void setUp() {
		this.category = new Category();
	}
	
	@Test
	public void getId() throws Exception {
		Long idValue = 4L;
		
		this.category.setId(idValue);
		
		assertEquals(idValue, this.category.getId());
	}
	
	@Test
	public void getDescription() throws Exception {
		String descValue = "example description";
				
		this.category.setDescription(descValue);
		
		assertEquals(descValue, this.category.getDescription());
	}
	
	@Test
	public void getRecipes() throws Exception {
		Set<Recipe> recipes = new HashSet<>();
		
		Recipe recipe = new Recipe();
		recipe.setId(3L);
		recipe.setDescription("sample recipe");
		
		recipes.add(recipe);
		
		this.category.setRecipes(recipes);
		
		assertEquals(recipes, this.category.getRecipes());
	}
}
