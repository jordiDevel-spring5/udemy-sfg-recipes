package guru.springframework.sfgrecipes.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import guru.springframework.sfgrecipes.domain.Recipe;
import guru.springframework.sfgrecipes.repositories.RecipeRepository;

public class RecipeServiceTest {

	RecipeServiceImpl recipeService;
	
	@Mock
	RecipeRepository recipeRepository;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		this.recipeService = new RecipeServiceImpl(this.recipeRepository);
	}
	
	@Test
	public void findAll() throws Exception {
		Set<Recipe> recipesData = new HashSet<>();
		
		Recipe recipe = new Recipe();
		recipe.setId(3L);
		recipe.setDescription("sample recipe");
		
		recipesData.add(recipe);
		
		when(this.recipeRepository.findAll()).thenReturn(recipesData);
		
		Set<Recipe> recipes = this.recipeService.findAll();
		
		assertEquals(1, recipes.size());
		verify(this.recipeRepository, times(1)).findAll();
	}
}
