package guru.springframework.sfgrecipes.converters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import guru.springframework.sfgrecipes.commands.NotesCommand;
import guru.springframework.sfgrecipes.domain.Notes;

public class NotesCommandToNotesTest {

	public static final String DESCRIPTION = "description";
	public static final Long LONG_VALUE = 1L;
	
	NotesCommandToNotes converter;
	
	@Before
	public void setUp() throws Exception {
		this.converter = new NotesCommandToNotes();
	}

	@Test
	public void testNullParameter() throws Exception {
		assertNull(this.converter.convert(null));
	}

	@Test
	public void testEmptyObject() throws Exception {
		assertNotNull(this.converter.convert(new NotesCommand()));
	}
	
	@Test
	public void testConvert() {
		//given
		NotesCommand notesCmd = new NotesCommand();
		notesCmd.setId(LONG_VALUE);
		notesCmd.setRecipeNotes(DESCRIPTION);
		
		//when
		Notes notes = this.converter.convert(notesCmd);
		
		//then
		assertNotNull(notes);
		assertEquals(LONG_VALUE, notes.getId());
		assertEquals(DESCRIPTION, notes.getRecipeNotes());
	}

}
