package com.example.demo.service;

import com.example.demo.dto.CommentDTO;

public interface CommentService {
	
	public CommentDTO creteComment(long publicationId, CommentDTO commentDTO);

}
