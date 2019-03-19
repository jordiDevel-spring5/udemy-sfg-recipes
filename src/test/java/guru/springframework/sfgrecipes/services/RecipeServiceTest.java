package guru.springframework.sfgrecipes.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.anyLong;

import java.util.HashSet;
import java.util.Optional;
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
	
	final Long recipeId = 1L;
	final Recipe recipe = Recipe.builder().id(1L).description("sample recipe 1").build();
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		this.recipeService = new RecipeServiceImpl(this.recipeRepository, null, null);
	}
	
	@Test
	public void findAll() throws Exception {
		Set<Recipe> recipesData = new HashSet<>();
		recipesData.add(this.recipe);
		
		when(this.recipeRepository.findAll()).thenReturn(recipesData);
		
		Set<Recipe> recipes = this.recipeService.findAll();
		
		assertEquals(1, recipes.size());
		verify(this.recipeRepository, times(1)).findAll();
	}
	
	@Test
	public void findById() throws Exception {
		when(this.recipeRepository.findById(this.recipeId)).thenReturn(Optional.of(this.recipe));
		
		Recipe findRecipe = this.recipeService.findById(this.recipeId);
		
		assertNotNull(findRecipe);
		assertEquals(this.recipeId, findRecipe.getId());
		
		verify(this.recipeRepository, times(1)).findById(anyLong());
	}
	
	@Test
	public void deleteById() throws Exception {
		Long idDelete = 2L;
		
		this.recipeService.deleteById(idDelete);
		
		verify(this.recipeRepository, times(1)).deleteById(anyLong());
	}
}
