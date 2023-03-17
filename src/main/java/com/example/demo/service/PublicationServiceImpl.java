package com.example.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.PublicationDTO;
import com.example.demo.entities.Publication;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.repository.PublicationRepo;

@Service
public class PublicationServiceImpl implements PublicationService{
	
	@Autowired
	private PublicationRepo publicationRepo;

	@Override
	public PublicationDTO createPublication(PublicationDTO publicationDTO) {
		// Convertimos de DTO a entidad
		Publication publication = new Publication();
		publication = mapDTOToEntity(publicationDTO);
		Publication newPublication = publicationRepo.save(publication);
//		convertimos de entidad a DTO		
		PublicationDTO publicationAnswer = new PublicationDTO();
		publicationAnswer = mapEntityToDTO(newPublication);
		return publicationAnswer ;
	}

	@Override
	public List<PublicationDTO> getAllPublication() {
		List<Publication> publications = publicationRepo.findAll();
		return publications.stream().map(publication -> mapEntityToDTO(publication)).collect(Collectors.toList());
	}
	
	@Override
	public PublicationDTO getPublicationById(long id) {
		Publication publication = publicationRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Publication", "id", id));
		return mapEntityToDTO(publication);
	}
	
	@Override
	public PublicationDTO uptadePublication(PublicationDTO publicationDTO, long id) {
		Publication publication = publicationRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Publication", "id", id));
		publication.setTitle(publicationDTO.getTitle());
		publication.setContent(publicationDTO.getContent());
		publication.setDescription(publicationDTO.getDescription());
		Publication newPublicationUpdate = publicationRepo.save(publication);
		return mapEntityToDTO(newPublicationUpdate);
	}
	
	@Override
	public void deletePublication(long id) {
		Publication publication = publicationRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Publication", "id", id));
		publicationRepo.delete(publication);
	}
	
	private PublicationDTO mapEntityToDTO(Publication publication) {
		PublicationDTO publicationDTO = new PublicationDTO();
		publicationDTO.setId(publication.getId());
		publicationDTO.setTitle(publication.getTitle());
		publicationDTO.setContent(publication.getContent());
		publicationDTO.setDescription(publication.getDescription());
		return publicationDTO;
	}
	
	private Publication mapDTOToEntity(PublicationDTO publicationDTO) {
		Publication publication = new Publication();
		publication.setId(publicationDTO.getId());
		publication.setTitle(publicationDTO.getTitle());
		publication.setContent(publicationDTO.getContent());
		publication.setDescription(publicationDTO.getDescription());
		return publication;
	}

}
