package com.example.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.example.demo.dto.PublicationDTO;
import com.example.demo.dto.PublicationResponse;
import com.example.demo.entities.Publication;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.repository.PublicationRepo;

@Service
public class PublicationServiceImpl implements PublicationService{
	
	@Autowired
	ModelMapper modelMapper;
	
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
	public PublicationResponse getAllPublication(int numberOfPage, int sizeOfPage, String orderBy, String sortDir) {
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(orderBy).ascending():Sort.by(orderBy).descending();
		Pageable pageable = PageRequest.of(numberOfPage,sizeOfPage, sort);
		Page<Publication> publications = publicationRepo.findAll(pageable);
		List<Publication> Listpublications = publications.getContent();
//		List<Publication> Listpublications = publicationRepo.findAll();
		List<PublicationDTO> content = Listpublications.stream().map(publication -> mapEntityToDTO(publication)).collect(Collectors.toList());
		
		PublicationResponse publicationResponse = new PublicationResponse();
		publicationResponse.setContent(content);
		publicationResponse.setNumberPage(publications.getNumber());
		publicationResponse.setPageSize(publications.getSize());
		publicationResponse.setTotalElements(publications.getTotalElements());
		publicationResponse.setTotalPages(publications.getTotalPages());
		publicationResponse.setLast(publications.isLast());
		
		return publicationResponse;
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
		PublicationDTO publicationDTO = modelMapper.map(publication, PublicationDTO.class);
		return publicationDTO;
	}
	
	private Publication mapDTOToEntity(PublicationDTO publicationDTO) {
		Publication publication = modelMapper.map(publicationDTO, Publication.class);
		return publication;
	}

}
