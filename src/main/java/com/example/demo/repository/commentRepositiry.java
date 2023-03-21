package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Comment;

public interface commentRepositiry extends JpaRepository<Comment, Long>{

}
