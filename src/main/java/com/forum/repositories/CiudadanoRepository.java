package com.forum.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.forum.domain.Ciudadano;

public interface CiudadanoRepository extends JpaRepository<Ciudadano, Long> {
	
	

}
