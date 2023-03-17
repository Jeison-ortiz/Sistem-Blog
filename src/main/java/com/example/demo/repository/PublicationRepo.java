package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Publication;

public interface PublicationRepo extends JpaRepository<Publication, Long>{

}
