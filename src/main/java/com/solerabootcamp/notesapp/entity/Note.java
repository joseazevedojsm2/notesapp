package com.solerabootcamp.notesapp.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Note {
	@Id
	@GeneratedValue
	private int id;
	
    private String description;
    private String text;
    private LocalDate  creationDate;
    private LocalDate estimatedCompletionDate; 
    private LocalDate completionDate;
    private String link;
    private String mentions;
    private int priority;

}
