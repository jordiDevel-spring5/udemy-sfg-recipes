package guru.springframework.sfgrecipes.services;

import java.util.Optional;
import java.util.Set;

import guru.springframework.sfgrecipes.commands.UnitOfMeasureCommand;
import guru.springframework.sfgrecipes.domain.UnitOfMeasure;

public interface UnitOfMeasureService {

	Optional<UnitOfMeasure> findByDescription(String description);

	Set<UnitOfMeasureCommand> listAllUoms();
	
}
