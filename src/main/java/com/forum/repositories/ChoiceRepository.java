package com.forum.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.forum.domain.Choice;

public interface ChoiceRepository extends JpaRepository<Choice, Long> {
	
	@Override
	public Optional<Choice> findById(Long choiceId); 	
	
}
