package com.example.demo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class CommentDTO {

	private long id;

	@NotEmpty(message = "El nombre no debe ser vacio")
	private String name;

	@Email
	@NotEmpty(message = "El email no puede ser vacio")
	private String email;

	@NotEmpty
	@Size(min = 10, message = "El cuerpo del comentario debe tener almenos 10 caracteres")
	private String body;

	public CommentDTO() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
}
