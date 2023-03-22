package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.CommentDTO;
import com.example.demo.service.CommentService;

@RestController
@RequestMapping("/api")
public class CommentController {

	@Autowired
	private CommentService commentService;

	@GetMapping("/publications/{publicationId}/comments")
	public List<CommentDTO> listCommentsByPublicaion(@PathVariable(name = "publicationId") long id) {
		return commentService.getCommentsById(id);
	}

	@GetMapping("/publications/{publicationId}/comments/{id}")
	public ResponseEntity<CommentDTO> getCommentById(@PathVariable(value = "publicationId") Long publicationId,
			@PathVariable(value = "id") Long commentId) {
			CommentDTO commentDTO = commentService.getCommentById(publicationId, commentId);
		return new ResponseEntity<> (commentDTO,HttpStatus.OK);
	}

	@PostMapping("/publications/{publicationId}/comment/")
	public ResponseEntity<CommentDTO> saveComment(@PathVariable(name = "publicationId") long publicationId,
			@RequestBody CommentDTO commentDTO) {
		return new ResponseEntity<>(commentService.creteComment(publicationId, commentDTO), HttpStatus.CREATED);
	}
	
	@PutMapping("/publications/{publicationId}/comments/{commentId}")
	public ResponseEntity<CommentDTO> updateComment(@PathVariable(name="publicationId")Long id,
			@PathVariable(value="commentId")Long commentId,
			@RequestBody CommentDTO commentDTO){
		return new ResponseEntity<>(commentService.updateComment(id, commentId, commentDTO),HttpStatus.OK);
		
	}

}
