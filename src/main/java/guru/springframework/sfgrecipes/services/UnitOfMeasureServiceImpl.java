package guru.springframework.sfgrecipes.services;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import guru.springframework.sfgrecipes.commands.UnitOfMeasureCommand;
import guru.springframework.sfgrecipes.converters.UnitOfMeasureToUnitOfMeasureCommand;
import guru.springframework.sfgrecipes.domain.UnitOfMeasure;
import guru.springframework.sfgrecipes.repositories.UnitOfMeasureRepository;

@Service
public class UnitOfMeasureServiceImpl implements UnitOfMeasureService {

	private final UnitOfMeasureRepository unitOfMeasureRepository;
	private final UnitOfMeasureToUnitOfMeasureCommand uniOfMeasureToUnitOfMeasureCommand;

	public UnitOfMeasureServiceImpl(UnitOfMeasureRepository unitOfMeasureRepository,
			UnitOfMeasureToUnitOfMeasureCommand uniOfMeasureToUnitOfMeasureCommand) {
		this.unitOfMeasureRepository = unitOfMeasureRepository;
		this.uniOfMeasureToUnitOfMeasureCommand = uniOfMeasureToUnitOfMeasureCommand;
	}

	@Override
	public Optional<UnitOfMeasure> findByDescription(String description) {
		return this.unitOfMeasureRepository.findByDescription(description);
	}

	@Override
	public Set<UnitOfMeasureCommand> listAllUoms() {
		return StreamSupport.stream(this.unitOfMeasureRepository.findAll().spliterator(), false)
			.map(this.uniOfMeasureToUnitOfMeasureCommand::convert)
			.collect(Collectors.toSet());
	}
	
}
