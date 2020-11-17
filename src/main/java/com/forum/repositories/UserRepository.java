package com.forum.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.forum.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	Optional<User> findByEmail(String email);
	Optional<User> findByAccountName(String accountName);
	Optional<User> findByResetPasswordCode(String resetPasswordCode);
	User findByVerificationCode(String verificationCode);
	List<User> findByNameIgnoreCaseContaining(String name);
}
