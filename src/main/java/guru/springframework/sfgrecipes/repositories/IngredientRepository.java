package guru.springframework.sfgrecipes.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import guru.springframework.sfgrecipes.domain.Ingredient;

@Repository
public interface IngredientRepository extends CrudRepository<Ingredient, Long> {

	Optional<Ingredient> findByRecipeIdAndId(Long recipeId, Long id);
	
}
