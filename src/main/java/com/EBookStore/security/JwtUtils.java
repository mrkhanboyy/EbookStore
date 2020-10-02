package com.EBookStore.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.EBookStore.model.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtUtils {
	
	
	private String key = "THISISSECRETKEYNUMBER1THISISSECRETKEYNUMBER1THISISSECRETKEYNUMBER1THISISSECRETKEYNUMBER1THISISSECRETKEYNUMBER1THISISSECRETKEYNUMBER1";
	
	@Value("18000") //in seconds (5 hours)
    private Long expiration;
	
	
	public String generateToken(User user) {
		Map<String, Object> claims = new HashMap<>();
        claims.put("sub", user.getUsername());
        claims.put("created", new Date(System.currentTimeMillis()));
        return Jwts.builder().setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000))
                .signWith(Keys.hmacShaKeyFor(key.getBytes()) ).compact();
	}
	
	public boolean validateToken(String jwtToken, UserDetails userDetails) {
		 Jwts.parser().setSigningKey(key.getBytes()).parseClaimsJws(jwtToken);
		 final String username = getUsernameFromToken(jwtToken.replace("Bearer ", ""));
	        return username.equals(userDetails.getUsername())
	                && !isTokenExpired(jwtToken);
	}
	
	private boolean isTokenExpired(String jwtToken) {
		 Date expiration = null;
		try {
			expiration = getExpirationDateFromToken(jwtToken);
		}catch(Exception e) {
			e.printStackTrace();
		}
        return expiration.before(new Date(System.currentTimeMillis()));
	}

	private Date getExpirationDateFromToken(String jwtToken) {
		 Date expiration;
	        try {
	            final Claims claims = getClaimsFromToken(jwtToken.replace("Bearer ", ""));
	            expiration = (Date)(claims.getExpiration());
	        } catch (Exception e) {
	        	e.printStackTrace();
	            expiration = null;
	        }
	        return expiration;
	}

	private Claims getClaimsFromToken(String jwtToken) {
		Claims claims;
        try {
            claims = Jwts.parser().setSigningKey(key.getBytes())
                    .parseClaimsJws(jwtToken).getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
	}

	public String getUsernameFromToken(String jwtToken) {
		String username;
        try {
        	
            Claims claims = getClaimsFromToken(jwtToken.replace("Bearer ", ""));
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
				
	}

}
