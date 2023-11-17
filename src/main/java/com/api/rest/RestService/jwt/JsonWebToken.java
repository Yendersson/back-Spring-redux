package com.api.rest.RestService.jwt;

import java.util.Date;
import java.util.UUID;

import com.api.rest.RestService.entities.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JsonWebToken {
	
    static long nowMillis = System.currentTimeMillis();
    static Date now = new Date(nowMillis);
    private static String secretKey = "asdfSFS34wfsdfsdfSDSD32dfsddDDerQSNCK34SOWEK5354fdgdf4";
	
	public static String generateToken(User user) {
        String token = Jwts.builder()
        		.claim("username", user.getUsername())
        		.claim("id", user.getId())
        		.setSubject("USER")
        		.setIssuedAt(now)
        		.setExpiration(new Date(nowMillis + 60000))
        		.signWith(SignatureAlgorithm.HS256, secretKey).
        		compact();
        
        return token;
	}
	
	public static Boolean evaluateToken(String token) {
			try {
				Claims claim = Jwts.parser()
						.setSigningKey(secretKey)
						.parseClaimsJws(token.replaceAll("\"", ""))
						.getBody();
				
				return true;
			} catch (Exception e) {
				System.out.println(e.getMessage());
				return false;
			}
	}
	
	
	
}
