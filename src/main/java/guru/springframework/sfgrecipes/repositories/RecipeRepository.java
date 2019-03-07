package guru.springframework.sfgrecipes.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import guru.springframework.sfgrecipes.domain.Recipe;

@Repository
public interface RecipeRepository extends CrudRepository<Recipe, Long> {}
