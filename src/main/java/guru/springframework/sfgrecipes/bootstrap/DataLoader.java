package guru.springframework.sfgrecipes.bootstrap;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import guru.springframework.sfgrecipes.domain.Category;
import guru.springframework.sfgrecipes.domain.Difficulty;
import guru.springframework.sfgrecipes.domain.Ingredient;
import guru.springframework.sfgrecipes.domain.Notes;
import guru.springframework.sfgrecipes.domain.Recipe;
import guru.springframework.sfgrecipes.domain.UnitOfMeasure;
import guru.springframework.sfgrecipes.services.CategoryService;
import guru.springframework.sfgrecipes.services.RecipeService;
import guru.springframework.sfgrecipes.services.UnitOfMeasureService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class DataLoader implements CommandLineRunner {

	private final RecipeService recipeService;
	private final CategoryService categoryService;
	private final UnitOfMeasureService unitOfMeasureService;
	
	public DataLoader(RecipeService recipeService, CategoryService categoryService, UnitOfMeasureService unitOfMeasureService) {
		this.recipeService = recipeService;
		this.categoryService = categoryService;
		this.unitOfMeasureService = unitOfMeasureService;
	}

	@Override
	public void run(String... args) throws Exception {
		this.addPerfectGuacamoleRecipe();
		this.addSpicyGrilledChickenRecipe();
	}

	@Transactional
	private void addPerfectGuacamoleRecipe() {
		Optional<UnitOfMeasure> dash = this.unitOfMeasureService.findByDescription("Dash");
		if (!dash.isPresent()) {
			throw new RuntimeException("Dash Unit Of Measure not found");
		}
		
		Optional<UnitOfMeasure> teaspoon = this.unitOfMeasureService.findByDescription("Teaspoon");
		if (!teaspoon.isPresent()) {
			throw new RuntimeException("Teaspoon Unit Of Measure not found");
		}
		
		Optional<Category> mexican = this.categoryService.findByDescription("Mexican");
		if (!mexican.isPresent()) {
			throw new RuntimeException("Mexican Category not found");
		}
		
		Recipe guacamole = new Recipe();
		guacamole.setDescription("How to Make Perfect Guacamole");
		guacamole.setPrepTime(10);
		guacamole.setCookTime(0);
		guacamole.setServings(4);
		guacamole.setSource("Simply Recipes");
		guacamole.setUrl("https://www.simplyrecipes.com/recipes/perfect_guacamole/");
		guacamole.setDifficulty(Difficulty.EASY);
		
		StringBuilder sb = new StringBuilder();
		sb.append("1. Cut avocado, remove flesh: Cut the avocados in half. Remove seed. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon. Place in a bowl.");
		sb.append("2. Mash with a fork: Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky)");
		sb.append("3. Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown. Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary individually in their hotness. So, start with a half of one chili pepper and add to the guacamole to your desired degree of hotness.");
		sb.append("Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste.");
		sb.append("4. Cover with plastic and chill to store: Place plastic wrap on the surface of the guacamole cover it and to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.) Refrigerate until ready to serve.");
		sb.append("Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, add it just before serving.");
		guacamole.setDirections(sb.toString());
		
		guacamole.setNotes(new Notes("Guacamole notes"));
		
		guacamole.addIngredient(new Ingredient("ripe avocados", new BigDecimal("2"), dash.get()));
		guacamole.addIngredient(new Ingredient("Kosher salt", new BigDecimal("0.5"), teaspoon.get()));
		
		guacamole.getCategories().add(mexican.get());
		
		this.recipeService.saveRecipe(guacamole);
		
		log.info("Perfect Guacamole Recipe saved with id: " + guacamole.getId());
	}

	@Transactional
	private void addSpicyGrilledChickenRecipe() {
		Optional<UnitOfMeasure> tablespoon = this.unitOfMeasureService.findByDescription("Tablespoon");
		if (!tablespoon.isPresent()) {
			throw new RuntimeException("Tablespoon Unit Of Measure not found");
		}
		
		Optional<Category> mexican = this.categoryService.findByDescription("Mexican");
		if (!mexican.isPresent()) {
			throw new RuntimeException("Mexican Category not found");
		}
		
		Recipe chicken = new Recipe();
		
		chicken.setDescription("Spicy Grilled Chicken Tacos");
		chicken.setPrepTime(20);
		chicken.setCookTime(15);
		chicken.setServings(6);
		chicken.setSource("Simply Recipes");
		chicken.setUrl("https://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/");
		chicken.setDifficulty(Difficulty.MODERATE);
		
		StringBuilder sb = new StringBuilder();
		sb.append("1 Prepare a gas or charcoal grill for medium-high, direct heat.");
		sb.append("2 Make the marinade and coat the chicken: In a large bowl, stir together the chili powder, oregano, cumin, sugar, salt, garlic and orange zest. Stir in the orange juice and olive oil to make a loose paste. Add the chicken to the bowl and toss to coat all over.");
		sb.append("Set aside to marinate while the grill heats and you prepare the rest of the toppings.");
		sb.append("3 Grill the chicken: Grill the chicken for 3 to 4 minutes per side, or until a thermometer inserted into the thickest part of the meat registers 165F. Transfer to a plate and rest for 5 minutes.");
		sb.append("4 Warm the tortillas: Place each tortilla on the grill or on a hot, dry skillet over medium-high heat. As soon as you see pockets of the air start to puff up in the tortilla, turn it with tongs and heat for a few seconds on the other side.");
		sb.append("Wrap warmed tortillas in a tea towel to keep them warm until serving.");
		sb.append("5 Assemble the tacos: Slice the chicken into strips. On each tortilla, place a small handful of arugula. Top with chicken slices, sliced avocado, radishes, tomatoes, and onion slices. Drizzle with the thinned sour cream. Serve with lime wedges.");
		chicken.setDirections(sb.toString());
		
		chicken.setNotes(new Notes("Chicken notes"));
		
		chicken.addIngredient(new Ingredient("ancho chili powder", new BigDecimal("2"), tablespoon.get()));
		
		chicken.getCategories().add(mexican.get());
		
		this.recipeService.saveRecipe(chicken);
		
		log.info("Spicy Grilled Chicken Recipe saved with id: " + chicken.getId());
	}

}
