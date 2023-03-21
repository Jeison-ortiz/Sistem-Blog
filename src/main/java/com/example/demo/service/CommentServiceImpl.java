package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.CommentDTO;
import com.example.demo.entities.Comment;
import com.example.demo.entities.Publication;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.repository.PublicationRepo;
import com.example.demo.repository.commentRepositiry;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private commentRepositiry commentRepository;

	@Autowired
	private PublicationRepo publicationRepo;

	@Override
	public CommentDTO creteComment(long publicationId, CommentDTO commentDTO) {
		Comment comment = mapperCommentDTOToCommentEntity(commentDTO);
		Publication publication = publicationRepo.findById(publicationId)
				.orElseThrow(() -> new ResourceNotFoundException("PublicationId", "publicationId", publicationId));
		comment.setPublication(publication);
		Comment newCommet = commentRepository.save(comment);
		return mapperCommentEntityToCommentDTO(newCommet);
	}

	private CommentDTO mapperCommentEntityToCommentDTO(Comment comment) {
		CommentDTO commentDTO = new CommentDTO();
		commentDTO.setId(comment.getId());
		commentDTO.setName(comment.getName());
		commentDTO.setEmail(comment.getEmail());
		commentDTO.setBody(comment.getBody());
		return commentDTO;
	}

	private Comment mapperCommentDTOToCommentEntity(CommentDTO commentDTO) {
		Comment comment = new Comment();
		comment.setId(commentDTO.getId());
		comment.setName(commentDTO.getName());
		comment.setEmail(commentDTO.getEmail());
		comment.setBody(commentDTO.getBody());
		return comment;
	}

}
