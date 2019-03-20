package guru.springframework.sfgrecipes.controllers;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import guru.springframework.sfgrecipes.commands.IngredientCommand;
import guru.springframework.sfgrecipes.commands.RecipeCommand;
import guru.springframework.sfgrecipes.services.IngredientService;
import guru.springframework.sfgrecipes.services.RecipeService;
import guru.springframework.sfgrecipes.services.UnitOfMeasureService;

public class IngredientControllerTest {

	@Mock
	IngredientService ingredientService;
	
	@Mock
	RecipeService recipeService;
	
	@Mock
	UnitOfMeasureService unitOfMeasureService;
	
	IngredientController ingredientController;
	
	MockMvc mockMvc;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		this.ingredientController = new IngredientController(this.recipeService, this.ingredientService, this.unitOfMeasureService);
		this.mockMvc = MockMvcBuilders.standaloneSetup(this.ingredientController).build();
	}

	@Test
	public void testListIngredients() throws Exception {
		//given
		RecipeCommand recipeCmd = new RecipeCommand();
		when(this.recipeService.findCommandById(anyLong())).thenReturn(recipeCmd);
		
		//when
		this.mockMvc.perform(get("/recipe/1/ingredients"))
			.andExpect(status().isOk())
			.andExpect(view().name("recipe/ingredient/list"))
			.andExpect(model().attributeExists("recipe"));
		
		//then
		verify(this.recipeService, times(1)).findCommandById(anyLong());
	}
	
	@Test
	public void testShowIngredient() throws Exception {
		//given
		IngredientCommand ingredientCmd = new IngredientCommand();
		when(this.ingredientService.findCommandByRecipeIdAndId(anyLong(), anyLong())).thenReturn(ingredientCmd);
		
		//when
		this.mockMvc.perform(get("/recipe/1/ingredient/1/show"))
			.andExpect(status().isOk())
			.andExpect(view().name("recipe/ingredient/show"))
			.andExpect(model().attributeExists("ingredient"));
		
		//then
		verify(this.ingredientService, times(1)).findCommandByRecipeIdAndId(anyLong(), anyLong());
	}

	@Test
	public void testNewIngredientForm() throws Exception {
		//given
		RecipeCommand recipeCmd = new RecipeCommand();
		recipeCmd.setId(1L);
		
		//when
		when(this.recipeService.findCommandById(anyLong())).thenReturn(recipeCmd);
		when(this.unitOfMeasureService.listAllUoms()).thenReturn(new HashSet<>());
		
		//then
		this.mockMvc.perform(get("/recipe/1/ingredient/new"))
			.andExpect(status().isOk())
			.andExpect(view().name("recipe/ingredient/ingredientform"))
			.andExpect(model().attributeExists("ingredient"))
			.andExpect(model().attributeExists("uomList"));
		
		verify(this.recipeService, times(1)).findCommandById(anyLong());
	}
	
	@Test
	public void testUpdateIngredientForm() throws Exception {
		//given
		IngredientCommand ingredientCmd = new IngredientCommand();
		
		//when
		when(this.ingredientService.findCommandByRecipeIdAndId(anyLong(), anyLong())).thenReturn(ingredientCmd);
		when(this.unitOfMeasureService.listAllUoms()).thenReturn(new HashSet<>());
		
		//then		
		this.mockMvc.perform(get("/recipe/1/ingredient/1/update"))
			.andExpect(status().isOk())
			.andExpect(view().name("recipe/ingredient/ingredientform"))
			.andExpect(model().attributeExists("ingredient"))
			.andExpect(model().attributeExists("uomList"));
		
		verify(this.ingredientService, times(1)).findCommandByRecipeIdAndId(anyLong(), anyLong());
	}
	
	@Test
	public void testDeleteIngredientForm() throws Exception {
		//then		
		this.mockMvc.perform(get("/recipe/1/ingredient/1/delete"))
			.andExpect(status().is3xxRedirection())
			.andExpect(view().name("redirect:/recipe/1/ingredients"));
		
		verify(this.ingredientService, times(1)).deleteById(anyLong(), anyLong());
	}
}
