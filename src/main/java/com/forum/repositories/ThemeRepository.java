package com.forum.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.forum.domain.Theme;

public interface ThemeRepository extends JpaRepository<Theme, Long> {
	
	
}
