package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.PublicationDTO;
import com.example.demo.service.PublicationService;

@RestController
@RequestMapping("/api/publication")
public class PublicationController {

	@Autowired
	private PublicationService publicationService;

	@GetMapping("/getById/{id}")
	public ResponseEntity<PublicationDTO> getPublicationById(@PathVariable(name = "id") long id) {
		return ResponseEntity.ok(publicationService.getPublicationById(id));
	}

	@GetMapping("/getAll")
	public List<PublicationDTO> getAllPublications(
			@RequestParam(value = "pageNumber", defaultValue = "0", required = false) int numberOfPage,
			@RequestParam(value = "pageSize", defaultValue = "10", required = false) int sizeOfPage){
		return publicationService.getAllPublication(numberOfPage,sizeOfPage);
	}

	@PostMapping("/save")
	public ResponseEntity<PublicationDTO> savePublication(@RequestBody PublicationDTO publicationDTO) {
		return new ResponseEntity<>(publicationService.createPublication(publicationDTO), HttpStatus.CREATED);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<PublicationDTO> updatePublication(@RequestBody PublicationDTO publicationDTO,
			@PathVariable(name = "id") long id) {
		PublicationDTO publicationUpdate = publicationService.uptadePublication(publicationDTO, id);
		return new ResponseEntity<>(publicationUpdate, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deletePublication(@PathVariable(name = "id") long id) {
		publicationService.deletePublication(id);
		return new ResponseEntity<>("Publicacion eliminada con exito", HttpStatus.OK);
	}
}
