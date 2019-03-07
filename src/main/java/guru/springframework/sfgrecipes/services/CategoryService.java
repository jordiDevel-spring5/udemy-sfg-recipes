package guru.springframework.sfgrecipes.services;

import java.util.Optional;

import guru.springframework.sfgrecipes.domain.Category;

public interface CategoryService {

	Optional<Category> findByDescription(String description);
	
}
