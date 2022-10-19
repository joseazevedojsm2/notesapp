package com.solerabootcamp.notesapp.entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
    private List<String> mentions;
    private int priority;
    
    
	public Note() {	}


	public Note(int id, String description, String text, LocalDate creationDate, LocalDate estimatedCompletionDate,
			LocalDate completionDate, String link, List<String> mentions, int priority) {
		super();
		this.id = id;
		this.description = description;
		this.text = text;
		this.creationDate = creationDate;
		this.estimatedCompletionDate = estimatedCompletionDate;
		this.completionDate = completionDate;
		this.link = link;
		this.mentions = mentions;
		this.priority = priority;
	}





	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getText() {
		return text;
	}


	public void setText(String text) {
		this.text = text;
	}


	public LocalDate getCreationDate() {
		return creationDate;
	}


	public void setCreationDate(LocalDate creationDate) {
		this.creationDate = creationDate;
	}


	public LocalDate getEstimatedCompletionDate() {
		return estimatedCompletionDate;
	}


	public void setEstimatedCompletionDate(LocalDate estimatedCompletionDate) {
		this.estimatedCompletionDate = estimatedCompletionDate;
	}


	public LocalDate getCompletionDate() {
		return completionDate;
	}


	public void setCompletionDate(LocalDate completionDate) {
		this.completionDate = completionDate;
	}


	public String getLink() {
		return link;
	}


	public void setLink(String link) {
		this.link = link;
	}

	public List<String> getMentions() {
		return mentions;
	}


	public void setMentions(List<String> mentions) {
		this.mentions = mentions;
	}


	public int getPriority() {
		return priority;
	}


	public void setPriority(int priority) {
		this.priority = priority;
	}


	@Override
	public String toString() {
		return "Note [id=" + id + ", description=" + description + ", text=" + text + ", creationDate=" + creationDate
				+ ", estimatedCompletionDate=" + estimatedCompletionDate + ", completionDate=" + completionDate
				+ ", link=" + link + ", mentions=" + mentions + ", priority=" + priority + "]";
	}
    
	
    

}
