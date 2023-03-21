package com.example.demo.service;

import com.example.demo.dto.PublicationResponse;
import com.example.demo.dto.PublicationDTO;

public interface PublicationService {
	
	public PublicationDTO createPublication(PublicationDTO publicationDTO);
	
	public PublicationResponse getAllPublication(int numberOfPage, int sizeOfPage, String orderBy, String sortDir);
	
	public PublicationDTO getPublicationById(long id);
	
	public PublicationDTO uptadePublication(PublicationDTO publicationDTO, long id);
	
	public void deletePublication(long id);
	
}
