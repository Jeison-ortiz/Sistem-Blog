package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.CommentDTO;
import com.example.demo.service.CommentService;

@RestController
@RequestMapping("/api/")
public class CommentController {

	@Autowired
	private CommentService commentService;

	@PostMapping("publications/{publicationId}/comment")
	public ResponseEntity<CommentDTO> saveComment(@PathVariable(value = "publicationID") long publicationId,
			@RequestBody CommentDTO commentDTO) {
		return new ResponseEntity<>(commentService.creteComment(publicationId, commentDTO), HttpStatus.CREATED);
	}

}
