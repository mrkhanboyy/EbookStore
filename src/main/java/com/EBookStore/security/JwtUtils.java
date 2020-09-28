package com.EBookStore.security;

import java.sql.Date;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.EBookStore.model.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtUtils {
	
	@Value("${local.key}")
	private String key;
	
	public String generateToken(User user) {
		return Jwts.builder()
				.setSubject(user.getUsername())
				.setIssuedAt(Date.valueOf(LocalDate.now()))
				.setExpiration(Date.valueOf(LocalDate.now().plusWeeks(1)))
				.signWith(Keys.hmacShaKeyFor(key.getBytes()))
				.compact();
	}
	
	public boolean validateToken(String jwtToken) {
		 Jwts.parser().setSigningKey(key).parseClaimsJws(jwtToken);
		 return true;
	}
	
	public String getUsernameFromToken(String jwtToken) {
		Claims claims = Jwts.parser()
						.setSigningKey(key)
						.parseClaimsJws(jwtToken)
						.getBody();
		return claims.getSubject();
		
	}

}
