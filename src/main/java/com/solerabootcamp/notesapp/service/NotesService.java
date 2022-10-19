package com.solerabootcamp.notesapp.service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.solerabootcamp.notesapp.entity.Note;
import com.solerabootcamp.notesapp.repository.NotesRepository;

@Component
public class NotesService {
	
	
	private NotesRepository repository;
	
	@Autowired
	public NotesService(NotesRepository repository) {
		this.repository = repository;
	}

	public String recognizeLink(String link) {
		String regex = "^(.+)@(.+)$";
//		String regexYoutube = "(www\\.)?(youtube\\.com|youtu\\.be)\\/.+$";
		String regexPdf = "^(https?:\\/\\/)?www\\.([\\da-z\\.-]+)\\.([a-z\\.]{2,6})\\/[\\w \\.-]+?\\.pdf$";
		String regexWeb = "https?:\\/\\/(www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b([-a-zA-Z0-9()!@:%_\\+.~#?&\\/\\/=]*)";
		String socialMedia = "";
		String regexSocialMedia = "?:https?:)?\\/\\/(?:www\\.)?(?:"+socialMedia+"|fb)\\.com\\/(?P<profile>(?![A-z]+)";
		 
//		Pattern pattern = Pattern.compile(regexYoutube);
		Matcher matcher = pattern.matcher(link);

		if(matcher.matches()) 
			return link;
		return null;
	}

	public Note findById(int id) {
		Optional<Note> note = repository.findById(id);
		if(note.isEmpty())
			return null;
		return note.get();
	}


	public Note createNote(Note newNote) {
		if(newNote.getEstimatedCompletionDate().isBefore(newNote.getCreationDate()))
			return null;
		repository.save(newNote);
		return newNote;
	}

	public List<Note> findByPerson(String person) {
		List<Note> notes = repository.findByMentions(person);
		if(notes.isEmpty())
			return null;
		return notes;
	}

	public Note updateNotePriority(int id,int priority) {
		Optional<Note> note = repository.findById(id);
		
		if(note.isEmpty()) 
			return null;
		
		if(priority<0||priority>5)
			return null;
		
		note.get().setPriority(priority);
		
		return note.get();
	}

	public void deleteNote(int id) {
		repository.deleteById(id);
	}
	
	
	


}
