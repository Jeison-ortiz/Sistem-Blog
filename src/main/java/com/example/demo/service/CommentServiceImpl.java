package com.example.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.demo.dto.CommentDTO;
import com.example.demo.entities.Comment;
import com.example.demo.entities.Publication;
import com.example.demo.exceptions.BlogAppException;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.repository.PublicationRepo;
import com.example.demo.repository.CommentRepositiry;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	private CommentRepositiry commentRepository;

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

	@Override
	public List<CommentDTO> getCommentsById(long publicationId) {
		List<Comment> comments = commentRepository.findByPublicationId(publicationId);
		return comments.stream().map(comment -> mapperCommentEntityToCommentDTO(comment)).collect(Collectors.toList());
	}

	@Override
	public CommentDTO getCommentById(Long publicationId, Long commentId) {

		Publication publication = publicationRepo.findById(publicationId)
				.orElseThrow(() -> new ResourceNotFoundException("Publication", "Id", publicationId));

		Comment comment = commentRepository.findById(commentId)
				.orElseThrow(() -> new ResourceNotFoundException("Comment", "Id", commentId));
		if (!comment.getPublication().getId().equals(publication.getId())) {
			throw new BlogAppException(HttpStatus.BAD_REQUEST, "El comentario no pertenece a la publicación");
		}
		return mapperCommentEntityToCommentDTO(comment);
	}

	@Override
	public CommentDTO updateComment(Long publicationId, Long commentId, CommentDTO requestOfComment) {
		Publication publication = publicationRepo.findById(publicationId)
				.orElseThrow(() -> new ResourceNotFoundException("Publication", "Id", publicationId));

		Comment comment = commentRepository.findById(commentId)
				.orElseThrow(() -> new ResourceNotFoundException("Comment", "Id", commentId));

		if (!comment.getPublication().getId().equals(publication.getId())) {
			throw new BlogAppException(HttpStatus.BAD_REQUEST, "El comentario no pertenece a la publicación");
		}

		comment.setName(requestOfComment.getName());
		comment.setEmail(requestOfComment.getEmail());
		comment.setBody(requestOfComment.getBody());

		Comment commentUpdate = commentRepository.save(comment);

		return mapperCommentEntityToCommentDTO(commentUpdate);
	}
	
	@Override
	public void deleteComment(Long publicationId, Long commentId) {
		Publication publication = publicationRepo.findById(publicationId)
				.orElseThrow(() -> new ResourceNotFoundException("Publication", "Id", publicationId));

		Comment comment = commentRepository.findById(commentId)
				.orElseThrow(() -> new ResourceNotFoundException("Comment", "Id", commentId));
		
		if (!comment.getPublication().getId().equals(publication.getId())) {
			throw new BlogAppException(HttpStatus.BAD_REQUEST, "El comentario no pertenece a la publicación");
		}
		
		commentRepository.delete(comment);
		
	}

	private CommentDTO mapperCommentEntityToCommentDTO(Comment comment) {
		CommentDTO commentDTO = modelMapper.map(comment, CommentDTO.class);
		return commentDTO;
	}

	private Comment mapperCommentDTOToCommentEntity(CommentDTO commentDTO) {
		Comment comment = modelMapper.map(commentDTO,Comment.class);
		return comment;
	}

}
