package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.PublicationDTO;

public interface PublicationService {
	
	public PublicationDTO createPublication(PublicationDTO publicationDTO);
	
	public List<PublicationDTO> getAllPublication();
	
	public PublicationDTO getPublicationById(long id);
	
	public PublicationDTO uptadePublication(PublicationDTO publicationDTO, long id);
	
	public void deletePublication(long id);
	
}