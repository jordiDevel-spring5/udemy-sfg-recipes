package guru.springframework.sfgrecipes.services;

import java.util.Optional;

import guru.springframework.sfgrecipes.domain.UnitOfMeasure;

public interface UnitOfMeasureService {

	Optional<UnitOfMeasure> findByDescription(String description);
	
}
