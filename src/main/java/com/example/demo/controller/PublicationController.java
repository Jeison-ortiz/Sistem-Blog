package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.PublicationDTO;
import com.example.demo.service.PublicationService;

@RestController
@RequestMapping("/api/publication")
public class PublicationController {

	@Autowired
	private PublicationService publicationService;
	
	@PostMapping("/save")
	public ResponseEntity<PublicationDTO> savePublication(@RequestBody PublicationDTO publicationDTO){
		return new ResponseEntity<>(publicationService.createPublication(publicationDTO),HttpStatus.CREATED);
	}
	
}
