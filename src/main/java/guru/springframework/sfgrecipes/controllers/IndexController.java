package guru.springframework.sfgrecipes.controllers;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import guru.springframework.sfgrecipes.domain.Category;
import guru.springframework.sfgrecipes.domain.UnitOfMeasure;
import guru.springframework.sfgrecipes.services.CategoryService;
import guru.springframework.sfgrecipes.services.RecipeService;
import guru.springframework.sfgrecipes.services.UnitOfMeasureService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class IndexController {

	private final RecipeService recipeService;
	private final CategoryService categoryService;
	private final UnitOfMeasureService unitOfMeasureService;
	
	public IndexController(RecipeService recipeService, CategoryService categoryService, UnitOfMeasureService unitOfMeasureService) {
		this.recipeService = recipeService;
		this.categoryService = categoryService;
		this.unitOfMeasureService = unitOfMeasureService;
	}

	@RequestMapping({"", "/", "/index", "/index.html"})
	public String getIndexPage(Model model)
	{
		Optional<Category> categoryOptional = this.categoryService.findByDescription("American");
		Optional<UnitOfMeasure> unitOfMeasureOptional = this.unitOfMeasureService.findByDescription("Teaspoon");
		
		if (categoryOptional.isPresent()) {
			log.info("Cat Id is: " + categoryOptional.get().getId());
		}
		
		if (unitOfMeasureOptional.isPresent()) {
			log.info("UOM Id is: " + unitOfMeasureOptional.get().getId());
		}
		
		model.addAttribute("recipes", this.recipeService.findAll());
		
		return "index";
	}
}
