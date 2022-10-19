package com.solerabootcamp.notesapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.solerabootcamp.notesapp.entity.Note;

public interface NotesRepository extends JpaRepository<Note, Integer>{
	
	@Query(value = "SELECT * FROM Note WHERE ARRAY_CONTAINS(MENTIONS, ?1)", nativeQuery = true)
	List<Note> findByMentions(String mention);

}
