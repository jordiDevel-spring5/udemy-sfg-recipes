package guru.springframework.sfgrecipes.controllers;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import guru.springframework.sfgrecipes.domain.Recipe;
import guru.springframework.sfgrecipes.services.RecipeService;

public class RecipeControllerTest {

	RecipeController recipeController;
	
	@Mock
	RecipeService recipeService;
	
	@Mock
	Model model;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		this.recipeController = new RecipeController(this.recipeService);
	}

	@Test
	public void testGetRecipeById() {
		Recipe recipe = Recipe.builder().id(1L).description("sample recipe 1").build();
		
		when(this.recipeService.findById(anyLong())).thenReturn(recipe);
		
		ArgumentCaptor<Recipe> argCaptor = ArgumentCaptor.forClass(Recipe.class);
		
		String template = this.recipeController.getRecipeById("1", this.model);
		
		assertEquals("recipe/show", template);
		verify(this.recipeService, times(1)).findById(anyLong());
		verify(this.model, times(1)).addAttribute(eq("recipe"), argCaptor.capture());
		assertEquals("1", argCaptor.getValue().getId().toString());
	}

}
