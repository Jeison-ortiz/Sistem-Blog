package com.example.demo.dto;

import java.util.Set;

import com.example.demo.entities.Comment;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class PublicationDTO {
	
	private Long id;
	
	@NotEmpty
	@Size(min = 2, message = "El titulo debe tener al menos dos caracteres")
	private String title;
	
	@NotEmpty
	@Size(min = 10, message = "la descripción debe tener al menos 10 caracteres")
	private String description;
	
	@NotEmpty
	private String content;
	
	private Set<Comment> comments;
	
	public PublicationDTO(Long id, String title, String description, String content) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.content = content;
	}

	public PublicationDTO() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Set<Comment> getComments() {
		return comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

}
