package guru.springframework.sfgrecipes.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import guru.springframework.sfgrecipes.domain.Category;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {}
