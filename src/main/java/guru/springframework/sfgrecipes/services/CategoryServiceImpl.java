package guru.springframework.sfgrecipes.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import guru.springframework.sfgrecipes.domain.Category;
import guru.springframework.sfgrecipes.repositories.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {

	private final CategoryRepository categoryRepository;

	public CategoryServiceImpl(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	@Override
	public Optional<Category> findByDescription(String description) {
		return this.categoryRepository.findByDescription(description);
	}
	
}
