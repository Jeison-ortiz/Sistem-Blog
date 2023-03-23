package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.CommentDTO;

public interface CommentService {
	
	public CommentDTO creteComment(long publicationId, CommentDTO commentDTO);
	
	public List<CommentDTO> getCommentsById(long publicationId);
	
	public CommentDTO getCommentById(Long publicationId, Long commentID);
	
	public CommentDTO updateComment(Long publicationId, Long commentId, CommentDTO requestOfComment);
	
	public void deleteComment(Long publicationId, Long commentId);

}
