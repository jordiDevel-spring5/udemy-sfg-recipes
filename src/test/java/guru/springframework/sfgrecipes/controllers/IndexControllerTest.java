package guru.springframework.sfgrecipes.controllers;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

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
	public void getIndexPage() throws Exception {
		String template = this.indexController.getIndexPage(this.model);
		
		assertEquals("index", template);
		verify(this.model, times(1)).addAttribute(eq("recipes"), anySet());
		verify(this.recipeService, times(1)).findAll();
	}
}
