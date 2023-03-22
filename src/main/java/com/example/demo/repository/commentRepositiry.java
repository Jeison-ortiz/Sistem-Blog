package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Comment;

public interface CommentRepositiry extends JpaRepository<Comment, Long>{
	
	public List<Comment> findByPublicationId(long publicationId);
}
