package com.solerabootcamp.notesapp.service;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;

import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.AfterTestMethod;

import com.solerabootcamp.notesapp.entity.Note;
import com.solerabootcamp.notesapp.repository.NotesRepository;

@SpringBootTest
class NotesServiceTest {
	
	
	private NotesService service;	
	
	private static Note newNote;
	
	@Autowired
	public  NotesServiceTest (NotesService service) {
		this.service = service;
		newNote = new Note();		
		newNote.setCompletionDate(LocalDate.parse("2022-10-10"));
		newNote.setCreationDate(LocalDate.parse("2022-10-08"));
		newNote.setEstimatedCompletionDate(LocalDate.parse("2022-10-09"));
		newNote.setDescription("New note");
		newNote.setLink("www.facebook.com");
		newNote.setMentions(List.of(
			"www.facebook.com/tony"
		));
		newNote.setPriority(1);
		newNote.setText("Perfect social media");
		service.createNote(newNote);
	}
	

	@ParameterizedTest
	@ValueSource(ints = {1})
	public void findById_idExists_returnNote(int id) {
		Note note = service.findById(id);
		assertEquals(id, note.getId());
	}

	
	@ParameterizedTest
	@CsvSource({"2022-10-19,2022-10-18"})
	public void saveNote_estimatedDateSmallerCreatioDate_returnNull(String creationDate, String estimatedDate) {
		newNote.setCreationDate(LocalDate.parse(creationDate));
		newNote.setEstimatedCompletionDate(LocalDate.parse(estimatedDate));
		
		Note returnedNote = service.createNote(newNote);
		
		assertEquals(null, returnedNote);

	}
	
	@ParameterizedTest
	@CsvSource({"2022-10-19,2022-10-20"})

	public void saveNote_estimatedDateBiggerCreatioDate_returnNote(String creationDate, String estimatedDate) {
		newNote.setCreationDate(LocalDate.parse(creationDate));
		newNote.setEstimatedCompletionDate(LocalDate.parse(estimatedDate));
		System.out.println(newNote);

		Note returnedNote = service.createNote(newNote);
		
		assertEquals(0, returnedNote.getId());
	}	

	@ParameterizedTest
	@ValueSource(strings = {"www.facebook.com/tony"})
	public void findById_notesByMention_returnNote(String person) {
		List<Note> note = service.findByPerson(person);
		assertEquals(person, note.get(0).getMentions().get(0));
	}
	
	@ParameterizedTest
	@ValueSource(strings = {"www"})
	public void findById_notesByNotExistentMention_returnNull(String person) {
		List<Note> note = service.findByPerson(person);
		assertEquals(null, note);
	}
	
	@ParameterizedTest
	@CsvSource({"1,-1","1,-6"})
	public void updateNote_setPriorityNotBetweenZeroandFive_returnNote(int id,int priority) {
				
		Note returnedNote = service.updateNotePriority(id,priority);
		
		assertEquals(null, returnedNote);
		
	}
	
	@ParameterizedTest
	@CsvSource({"1,3"})
	public void updateNote_setPriorityBetweenZeroandFive_returnNote(int id,int priority) {
				
		Note returnedNote = service.updateNotePriority(id,priority);
		
		assertEquals(priority, returnedNote.getPriority());
		
	}
	
	
	@ParameterizedTest
	@ValueSource(ints = {2})
	public void deleteNote_validId_returnTrue(int id) {
		service.deleteNote(id);
		
		assertEquals(null,service.findById(id));
	}
	
	@ParameterizedTest
	@ValueSource(strings = {"www.youtube.com/"})
	public void saveNote_youtubeLink_returnLink(String link) {
		System.out.println(service.recognizeLink(link));
	}


	
}
