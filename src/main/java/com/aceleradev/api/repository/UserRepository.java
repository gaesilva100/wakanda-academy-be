package com.aceleradev.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.aceleradev.api.domain.model.User;

public interface UserRepository extends JpaRepository<User,Long> {
	
	@Query(name = "User.findByUsernameAndPassword")
	Optional<User> findByUsernameAndPassword(String username, String password);
	
	@Query(name = "User.findByEmail")
	Optional<User> findByEmail(String email);
	
}
