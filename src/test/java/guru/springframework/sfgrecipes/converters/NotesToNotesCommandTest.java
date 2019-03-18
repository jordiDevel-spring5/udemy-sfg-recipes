package guru.springframework.sfgrecipes.converters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import guru.springframework.sfgrecipes.commands.NotesCommand;
import guru.springframework.sfgrecipes.domain.Notes;

public class NotesToNotesCommandTest {

	public static final String DESCRIPTION = "description";
	public static final Long LONG_VALUE = 1L;
	
	NotesToNotesCommand converter;
	
	@Before
	public void setUp() throws Exception {
		this.converter = new NotesToNotesCommand();
	}

	@Test
	public void testNullParameter() throws Exception {
		assertNull(this.converter.convert(null));
	}

	@Test
	public void testEmptyObject() throws Exception {
		assertNotNull(this.converter.convert(new Notes()));
	}
	
	@Test
	public void testConvert() {
		//given
		Notes notes = new Notes();
		notes.setId(LONG_VALUE);
		notes.setRecipeNotes(DESCRIPTION);
		
		//when
		NotesCommand notesCmd = this.converter.convert(notes);
		
		//then
		assertNotNull(notesCmd);
		assertEquals(LONG_VALUE, notesCmd.getId());
		assertEquals(DESCRIPTION, notesCmd.getRecipeNotes());
	}

}
