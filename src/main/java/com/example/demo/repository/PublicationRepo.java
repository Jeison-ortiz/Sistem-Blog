package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Publications;

public interface PublicationRepo extends JpaRepository<Publications, Long>{

}
