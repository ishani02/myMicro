package com.example.identity.services;

import java.security.Key;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTService {
	public static final String SECRET = "cdd6f4840b269bd36f1731ac090dd03d62e3bbccd3b2d551f61ea77adb4b6548";
	
	public void validateToken(final String token){
		Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token);
	}
	
	public String generateToken(String username) {
		Map<String, Object>claims = new HashMap<>();
		return createToken(claims,username);
	}
	private String createToken(Map<String, Object> claims, String username) {
		return Jwts.builder()
				.setClaims(claims)
				.setSubject(username)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+1000*60*30))
				.signWith(getSignKey(),SignatureAlgorithm.HS256).compact();
	}

	private Key getSignKey() {
		byte[] keysByte = Decoders.BASE64.decode(SECRET);
		return Keys.hmacShaKeyFor(keysByte);
	}
}
