package guru.springframework.sfgrecipes.repositories;

import static org.junit.Assert.assertEquals;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import guru.springframework.sfgrecipes.domain.UnitOfMeasure;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UnitOfMeasureRepositoryIT {

	@Autowired
	UnitOfMeasureRepository unitOfMeasureRepository;
	
	@Before
	public void setUp() throws Exception {
	
	}
	
	@Test
	public void findByDescriptionTeaspoon() throws Exception {
		String desc = "Teaspoon";
		
		Optional<UnitOfMeasure> uomOpt = this.unitOfMeasureRepository.findByDescription(desc);
		
		assertEquals(desc, uomOpt.get().getDescription());
	}
	
	@Test
	public void findByDescriptionCup() throws Exception {
		String desc = "Cup";
		
		Optional<UnitOfMeasure> uomOpt = this.unitOfMeasureRepository.findByDescription(desc);
		
		assertEquals(desc, uomOpt.get().getDescription());
	}
}
