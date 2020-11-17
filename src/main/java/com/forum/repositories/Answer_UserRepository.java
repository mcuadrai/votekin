package com.forum.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.forum.domain.Answer_User;

public interface Answer_UserRepository extends JpaRepository<Answer_User, Long> {
	
	Answer_User findByAnswerIdAndUserId(Long answerId, Long userId);
}
