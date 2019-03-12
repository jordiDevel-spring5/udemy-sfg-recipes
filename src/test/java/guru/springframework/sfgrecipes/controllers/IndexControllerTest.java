package guru.springframework.sfgrecipes.controllers;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import guru.springframework.sfgrecipes.domain.Recipe;
import guru.springframework.sfgrecipes.services.CategoryService;
import guru.springframework.sfgrecipes.services.RecipeService;
import guru.springframework.sfgrecipes.services.UnitOfMeasureService;

public class IndexControllerTest {

	IndexController indexController;
	
	@Mock
	RecipeService recipeService;
	
	@Mock
	CategoryService categoryService;
	
	@Mock
	UnitOfMeasureService unitOfMeasureService;
	
	@Mock
	Model model;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		this.indexController = new IndexController(this.recipeService, this.categoryService, this.unitOfMeasureService);
	}
	
	@Test
	public void testMockMVC() throws Exception {
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(indexController).build();
		
		mockMvc.perform(get("/"))
			.andExpect(status().isOk())
			.andExpect(view().name("index"));
	}
	
	@Test
	public void getIndexPage() throws Exception {
		Set<Recipe> recipesData = new HashSet<>();
		recipesData.add(Recipe.builder().id(1L).description("sample recipe 1").build());
		recipesData.add(Recipe.builder().id(2L).description("sample recipe 2").build());
		
		when(this.recipeService.findAll()).thenReturn(recipesData);
		
		@SuppressWarnings("unchecked")
		ArgumentCaptor<Set<Recipe>> argCaptor = ArgumentCaptor.forClass(Set.class);
		
		String template = this.indexController.getIndexPage(this.model);
		
		assertEquals("index", template);
		verify(this.recipeService, times(1)).findAll();
		verify(this.model, times(1)).addAttribute(eq("recipes"), argCaptor.capture());
		assertEquals(2, argCaptor.getValue().size());
		
	}
}
