package com.EBookStore.security;

import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.EBookStore.exceptions.TokenNotFoundException;
import com.EBookStore.model.SessionToken;
import com.EBookStore.repository.SessionTokenRepo;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@Transactional
public class SessionTokenUtil {
	
	private final SessionTokenRepo sessionTokenRepo;

	public SessionToken generateSessionToken() {
		SessionToken st = new SessionToken();
		st.setToken(UUID.randomUUID().toString());
		return sessionTokenRepo.save(st);
	}
	
	public void validateSessionToken(String token) {
		sessionTokenRepo.findByToken(token)
			.orElseThrow(() -> new TokenNotFoundException("session token  \""+token+"\"  not found")); 
	}
	
	public void deleteSessionToken(String token) {
		sessionTokenRepo.deleteByToken(token);
	}
}
