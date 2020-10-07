package com.EBookStore.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.EBookStore.model.SessionToken;

public interface SessionTokenRepo extends JpaRepository<SessionToken, Long> {
	
	void deleteByToken(String token);
	Optional<SessionToken> findByToken(String token);

}
