package guru.springframework.sfgrecipes.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import guru.springframework.sfgrecipes.domain.UnitOfMeasure;
import guru.springframework.sfgrecipes.repositories.UnitOfMeasureRepository;

@Service
public class UnitOfMeasureServiceImpl implements UnitOfMeasureService {

	private final UnitOfMeasureRepository unitOfMeasureRepository;

	public UnitOfMeasureServiceImpl(UnitOfMeasureRepository unitOfMeasureRepository) {
		this.unitOfMeasureRepository = unitOfMeasureRepository;
	}

	@Override
	public Optional<UnitOfMeasure> findByDescription(String description) {
		return this.unitOfMeasureRepository.findByDescription(description);
	}
	
}
