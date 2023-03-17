package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.PublicationDTO;
import com.example.demo.entities.Publications;
import com.example.demo.repository.PublicationRepo;

@Service
public class PublicationServiceImpl implements PublicationService{
	
	@Autowired
	private PublicationRepo publicationRepo;

	@Override
	public PublicationDTO createPublication(PublicationDTO publicationDTO) {
		// Convertimos de DTO a entidad
		Publications publication = new Publications();
		publication = mapDTOToEntity(publicationDTO);
		Publications newPublication = publicationRepo.save(publication);
//		convertimos de entidad a DTO		
		PublicationDTO publicationAnswer = new PublicationDTO();
		publicationAnswer = mapEntityToDTO(newPublication);
		return publicationAnswer ;
	}

//	@Override
//	public List<PublicationDTO> getAllPublication() {
//		List<Publications> publications = publicationRepo.findAll();
//		return null;
//	}
	
	private PublicationDTO mapEntityToDTO(Publications publication) {
		PublicationDTO publicationDTO = new PublicationDTO();
		publicationDTO.setId(publication.getId());
		publicationDTO.setTitle(publication.getTitle());
		publicationDTO.setContent(publication.getContent());
		publicationDTO.setDescription(publication.getDescription());
		return publicationDTO;
	}
	
	private Publications mapDTOToEntity(PublicationDTO publicationDTO) {
		Publications publication = new Publications();
		publication.setId(publicationDTO.getId());
		publication.setTitle(publicationDTO.getTitle());
		publication.setContent(publicationDTO.getContent());
		publication.setDescription(publicationDTO.getDescription());
		return publication;
	}

}
