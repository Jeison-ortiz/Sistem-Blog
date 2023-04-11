package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
import com.example.demo.dto.PublicationResponse;
import com.example.demo.service.PublicationService;
import com.example.demo.util.AppConstans;

import jakarta.validation.Valid;

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
	public PublicationResponse getAllPublications(
			@RequestParam(value = "pageNumber", defaultValue = AppConstans.NUMBER_OF_PAGE_FOR_DEFAULT, required = false) int numberOfPage,
			@RequestParam(value = "pageSize", defaultValue = AppConstans.PAGE_SIZE_FOR_DEFAULT, required = false) int sizeOfPage,
			@RequestParam(value = "sortBy", defaultValue = AppConstans.ORDER_BY_DEFAULT, required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = AppConstans.ORDER_DIRECTION_FOR_DEFAULT, required = false) String sortDir){
		return publicationService.getAllPublication(numberOfPage,sizeOfPage, sortBy, sortDir);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/save")
	public ResponseEntity<PublicationDTO> savePublication(@Valid @RequestBody PublicationDTO publicationDTO) {
		return new ResponseEntity<>(publicationService.createPublication(publicationDTO), HttpStatus.CREATED);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/update/{id}")
	public ResponseEntity<PublicationDTO> updatePublication(@RequestBody PublicationDTO publicationDTO,
			@PathVariable(name = "id") long id) {
		PublicationDTO publicationUpdate = publicationService.uptadePublication(publicationDTO, id);
		return new ResponseEntity<>(publicationUpdate, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deletePublication(@PathVariable(name = "id") long id) {
		publicationService.deletePublication(id);
		return new ResponseEntity<>("Publicacion eliminada con exito", HttpStatus.OK);
	}
}
